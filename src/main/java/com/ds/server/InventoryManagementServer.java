package com.ds.server;

import assignment.distribute.system.NameServiceClient;
import com.ds.server.models.Order;
import com.ds.server.models.OrderResponse;
import com.ds.server.order.CheckOrderServiceImpl;
import com.ds.server.order.SetOrderServiceImpl;
import com.ds.server.product.CheckProductQuantityServiceImpl;
import com.ds.server.product.SetProductQuantityServiceImpl;
import com.ds.sycnhronization.DistributedLock;
import com.ds.sycnhronization.DistributedTx;
import com.ds.sycnhronization.DistributedTxCoordinator;
import com.ds.sycnhronization.DistributedTxParticipant;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class InventoryManagementServer {
    ArrayList<Order> ordersList = new ArrayList<>();
    private DistributedLock leaderLock;
    private AtomicBoolean isLeader = new AtomicBoolean(false);
    private byte[] leaderData;
    private int serverPort;
    private Map<String, Double> products = new HashMap();
    private Map<String, Order> orders = new HashMap();
    private DistributedTx productTransaction;

    public static final String NAME_SERVICE_ADDRESS = "http://localhost:2379";

    private DistributedTx orderTransaction;
    private SetProductQuantityServiceImpl setProductQuantityService;
    private CheckProductQuantityServiceImpl checkProductQuantityService;
    private SetOrderServiceImpl setOrderService;
    private CheckOrderServiceImpl checkOrderService;

    public InventoryManagementServer(String host, int port) throws InterruptedException, IOException, KeeperException {
        this.serverPort = port;
        leaderLock = new DistributedLock("InventoryManagementServer", buildServerData(host, port));

        setProductQuantityService = new SetProductQuantityServiceImpl(this);
        checkProductQuantityService = new CheckProductQuantityServiceImpl(this);
        setOrderService = new SetOrderServiceImpl(this);
        checkOrderService = new CheckOrderServiceImpl(this);
        productTransaction = new DistributedTxParticipant(setProductQuantityService);
        orderTransaction = new DistributedTxParticipant(setOrderService);
    }

    public static String buildServerData(String IP, int port) {
        StringBuilder builder = new StringBuilder();
        builder.append(IP).append(":").append(port);
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        DistributedTx.setZooKeeperURL("localhost:2181");
        if (args.length != 1) {
            System.out.println("Usage executable-name <port>");
        }

        int serverPort = Integer.parseInt(args[0]);
        DistributedLock.setZooKeeperURL("localhost:2181");

        InventoryManagementServer server = new InventoryManagementServer("localhost", serverPort);
        server.startServer();

        NameServiceClient client = new NameServiceClient(NAME_SERVICE_ADDRESS);
        client.registerService("InventoryManagementService", "127.0.0.1", serverPort, "tcp");
    }

    private void tryToBeLeader() throws KeeperException, InterruptedException {
        Thread leaderCampaignThread = new Thread(new LeaderCampaignThread());
        leaderCampaignThread.start();
    }

    public void startServer() throws IOException, InterruptedException, KeeperException {
        Server server = ServerBuilder
                .forPort(serverPort)
                .addService(checkProductQuantityService)
                .addService(setProductQuantityService)
                .addService(checkOrderService)
                .addService(setOrderService)
                .build();
        server.start();
        System.out.println("Inventory Management Server Started and ready to accept requests on port " + serverPort);

        tryToBeLeader();
        server.awaitTermination();
    }

    public DistributedTx getProductTransaction() {
        return productTransaction;
    }

    public DistributedTx getOrderTransaction() {
        return orderTransaction;
    }

    public boolean isLeader() {
        return isLeader.get();
    }

    public synchronized String[] getCurrentLeaderData() {
        return new String(leaderData).split(":");
    }

    private synchronized void setCurrentLeaderData(byte[] leaderData) {
        this.leaderData = leaderData;
    }

    public void setProductQuantity(String productId, double newQuantity) {
        if (products.containsKey(productId)) {
            // return the quantity of key product
            double quantity = products.get(productId);
            // update the quantity
            quantity = quantity + newQuantity;
            products.put(productId, quantity);
        } else {
            products.put(productId, newQuantity);
        }
    }

    public double getProductQuantity(String productId) {
        Double quantity = products.get(productId);
        return (quantity != null) ? quantity : 0.0;
    }

    public OrderResponse checkAvailabilityOfProduct(String product, double requestedQuantity) {
        OrderResponse response = new OrderResponse();
        double quantity = products.get(product);
        response.setAvailableQuantity(quantity);
        if (requestedQuantity > quantity) {
            System.out.println("Cannot process the order due to unavailable quantity");
            response.setStatus(false);
            return response;
        }
        response.setStatus(true);
        return response;
    }

    public OrderResponse setOrders(Order order) {
        OrderResponse response = new OrderResponse();
        try {
            double orderedQuantity = order.getQuantity();
            double quantity = products.get(order.getProduct());

            quantity -= orderedQuantity;

            products.put(order.getProduct(), quantity);
            orders.put(order.getOrderId(), order);

            response.setAvailableQuantity(quantity);
            response.setStatus(true);
        } catch (Exception ex) {
            System.out.println("Order failed.");
            response.setAvailableQuantity(0);
            response.setStatus(false);
            return response;
        }

        return response;
    }

    public Order getOrderProduct(String orderId) {
        Order order = orders.get(orderId);
        return (order != null) ? order : null;
    }

    public List<String[]> getOthersData() throws KeeperException, InterruptedException {
        List<String[]> result = new ArrayList<>();
        List<byte[]> othersData = leaderLock.getOthersData();

        for (byte[] data : othersData) {
            String[] dataStrings = new String(data).split(":");
            result.add(dataStrings);
        }
        return result;
    }

    private void beTheLeader() {
        System.out.println("I got the leader lock. Now acting as primary");
        isLeader.set(true);
        productTransaction = new DistributedTxCoordinator(setProductQuantityService);
        orderTransaction = new DistributedTxCoordinator(setOrderService);
    }

    class LeaderCampaignThread implements Runnable {
        private byte[] currentLeaderData = null;

        @Override
        public void run() {
            System.out.println("Starting the leader Campaign");

            try {
                boolean leader = leaderLock.tryAcquireLock();

                while (!leader) {
                    byte[] leaderData = leaderLock.getLockHolderData();
                    if (currentLeaderData != leaderData) {
                        currentLeaderData = leaderData;
                        setCurrentLeaderData(currentLeaderData);
                    }
                    Thread.sleep(10000);
                    leader = leaderLock.tryAcquireLock();
                }
                currentLeaderData = null;
                beTheLeader();
            } catch (Exception e) {
            }
        }
    }
}
