package ds.inventoryManagementSystem.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: InventoryManagementServer.proto")
public final class CheckOrderServiceGrpc {

  private CheckOrderServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.inventoryManagementSystem.grpc.generated.CheckOrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest,
      ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> getCheckOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkOrders",
      requestType = ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest.class,
      responseType = ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest,
      ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> getCheckOrdersMethod() {
    io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest, ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> getCheckOrdersMethod;
    if ((getCheckOrdersMethod = CheckOrderServiceGrpc.getCheckOrdersMethod) == null) {
      synchronized (CheckOrderServiceGrpc.class) {
        if ((getCheckOrdersMethod = CheckOrderServiceGrpc.getCheckOrdersMethod) == null) {
          CheckOrderServiceGrpc.getCheckOrdersMethod = getCheckOrdersMethod =
              io.grpc.MethodDescriptor.<ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest, ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckOrderServiceMethodDescriptorSupplier("checkOrders"))
              .build();
        }
      }
    }
    return getCheckOrdersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckOrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceStub>() {
        @java.lang.Override
        public CheckOrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckOrderServiceStub(channel, callOptions);
        }
      };
    return CheckOrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckOrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceBlockingStub>() {
        @java.lang.Override
        public CheckOrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckOrderServiceBlockingStub(channel, callOptions);
        }
      };
    return CheckOrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckOrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckOrderServiceFutureStub>() {
        @java.lang.Override
        public CheckOrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckOrderServiceFutureStub(channel, callOptions);
        }
      };
    return CheckOrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CheckOrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkOrders(ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest,
                ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse>(
                  this, METHODID_CHECK_ORDERS)))
          .build();
    }
  }

  /**
   */
  public static final class CheckOrderServiceStub extends io.grpc.stub.AbstractAsyncStub<CheckOrderServiceStub> {
    private CheckOrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckOrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckOrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkOrders(ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckOrdersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckOrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CheckOrderServiceBlockingStub> {
    private CheckOrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckOrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckOrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse checkOrders(ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckOrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CheckOrderServiceFutureStub> {
    private CheckOrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckOrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckOrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> checkOrders(
        ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckOrdersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ORDERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckOrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckOrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_ORDERS:
          serviceImpl.checkOrders((ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest) request,
              (io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CheckOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckOrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.inventoryManagementSystem.grpc.generated.InventoryManagementServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CheckOrderService");
    }
  }

  private static final class CheckOrderServiceFileDescriptorSupplier
      extends CheckOrderServiceBaseDescriptorSupplier {
    CheckOrderServiceFileDescriptorSupplier() {}
  }

  private static final class CheckOrderServiceMethodDescriptorSupplier
      extends CheckOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckOrderServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CheckOrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckOrderServiceFileDescriptorSupplier())
              .addMethod(getCheckOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
