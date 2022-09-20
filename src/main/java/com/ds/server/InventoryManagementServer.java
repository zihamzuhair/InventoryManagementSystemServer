package com.ds.server;

import com.ds.server.models.Order;
import com.ds.server.models.Product;
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
    private DistributedLock leaderLock;
    private AtomicBoolean isLeader = new AtomicBoolean(false);
    private byte[] leaderData;
    private int serverPort;
    private Map<String, Double> products = new HashMap();
    private Map<String, Double> orders = new HashMap();
    ArrayList<Order> ordersList = new ArrayList<>();



    private DistributedTx transaction;
    private SetProductQuantityServiceImpl setProductQuantityService;
    private CheckProductQuantityServiceImpl checkProductQuantityService;
    private SetOrderServiceImpl setOrderService;

    public static String buildServerData(String IP, int port) {
        StringBuilder builder = new StringBuilder();
        builder.append(IP).append(":").append(port);
        return builder.toString();
    }

    public InventoryManagementServer(String host, int port) throws InterruptedException, IOException, KeeperException {
        this.serverPort = port;
        leaderLock = new DistributedLock("InventoryManagementServer", buildServerData(host, port));

        setProductQuantityService = new SetProductQuantityServiceImpl(this);
        checkProductQuantityService = new CheckProductQuantityServiceImpl(this);
        setOrderService = new SetOrderServiceImpl(this);
        transaction = new DistributedTxParticipant(setProductQuantityService);
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
                .addService(setOrderService)
                .build();
        server.start();
        System.out.println("Inventory Management Server Started and ready to accept requests on port " + serverPort);

        tryToBeLeader();
        server.awaitTermination();
    }

    public DistributedTx getTransaction() {
        return transaction;
    }

    public boolean isLeader() {
        return isLeader.get();
    }

    private synchronized void setCurrentLeaderData(byte[] leaderData) {
        this.leaderData = leaderData;
    }

    public synchronized String[] getCurrentLeaderData() {
        return new String(leaderData).split(":");
    }

    public void setProductQuantity(String productId, double newQuantity) {
        if(products.containsKey(productId)){
            // return the quantity of key product
            double quantity = products.get(productId);
            // update the quantity
            quantity = quantity + newQuantity;
            products.put(productId, quantity);
        }else{
            products.put(productId, newQuantity);
        }
    }

    public double getProductQuantity(String productId) {
        Double quantity = products.get(productId);
        return (quantity != null) ? quantity : 0.0;
    }

    public void setOrders(Order orders) {
        ordersList.add(orders);
        System.out.println(ordersList.toString());
    }

    public double getOrderProduct(String orderId) {
        Double quantity = orders.get(orderId);
        return (quantity != null) ? quantity : 0.0;
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
        transaction = new DistributedTxCoordinator(setProductQuantityService);
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

    public static void main(String[] args) throws Exception {
        DistributedTx.setZooKeeperURL("localhost:2181");
        if (args.length != 1) {
            System.out.println("Usage executable-name <port>");
        }

        int serverPort = Integer.parseInt(args[0]);
        DistributedLock.setZooKeeperURL("localhost:2181");

        InventoryManagementServer server = new InventoryManagementServer("localhost", serverPort);
        server.startServer();
    }
}
