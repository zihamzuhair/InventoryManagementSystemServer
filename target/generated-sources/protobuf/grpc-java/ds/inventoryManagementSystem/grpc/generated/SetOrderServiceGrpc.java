package ds.inventoryManagementSystem.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: InventoryManagementServer.proto")
public final class SetOrderServiceGrpc {

  private SetOrderServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.inventoryManagementSystem.grpc.generated.SetOrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetOrderRequest,
      ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> getSetOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setOrders",
      requestType = ds.inventoryManagementSystem.grpc.generated.SetOrderRequest.class,
      responseType = ds.inventoryManagementSystem.grpc.generated.SetOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetOrderRequest,
      ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> getSetOrdersMethod() {
    io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetOrderRequest, ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> getSetOrdersMethod;
    if ((getSetOrdersMethod = SetOrderServiceGrpc.getSetOrdersMethod) == null) {
      synchronized (SetOrderServiceGrpc.class) {
        if ((getSetOrdersMethod = SetOrderServiceGrpc.getSetOrdersMethod) == null) {
          SetOrderServiceGrpc.getSetOrdersMethod = getSetOrdersMethod =
              io.grpc.MethodDescriptor.<ds.inventoryManagementSystem.grpc.generated.SetOrderRequest, ds.inventoryManagementSystem.grpc.generated.SetOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.SetOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.SetOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SetOrderServiceMethodDescriptorSupplier("setOrders"))
              .build();
        }
      }
    }
    return getSetOrdersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SetOrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceStub>() {
        @java.lang.Override
        public SetOrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetOrderServiceStub(channel, callOptions);
        }
      };
    return SetOrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SetOrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceBlockingStub>() {
        @java.lang.Override
        public SetOrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetOrderServiceBlockingStub(channel, callOptions);
        }
      };
    return SetOrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SetOrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetOrderServiceFutureStub>() {
        @java.lang.Override
        public SetOrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetOrderServiceFutureStub(channel, callOptions);
        }
      };
    return SetOrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SetOrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void setOrders(ds.inventoryManagementSystem.grpc.generated.SetOrderRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.inventoryManagementSystem.grpc.generated.SetOrderRequest,
                ds.inventoryManagementSystem.grpc.generated.SetOrderResponse>(
                  this, METHODID_SET_ORDERS)))
          .build();
    }
  }

  /**
   */
  public static final class SetOrderServiceStub extends io.grpc.stub.AbstractAsyncStub<SetOrderServiceStub> {
    private SetOrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetOrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetOrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void setOrders(ds.inventoryManagementSystem.grpc.generated.SetOrderRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetOrdersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SetOrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SetOrderServiceBlockingStub> {
    private SetOrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetOrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetOrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.inventoryManagementSystem.grpc.generated.SetOrderResponse setOrders(ds.inventoryManagementSystem.grpc.generated.SetOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SetOrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SetOrderServiceFutureStub> {
    private SetOrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetOrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetOrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> setOrders(
        ds.inventoryManagementSystem.grpc.generated.SetOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetOrdersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_ORDERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SetOrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SetOrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_ORDERS:
          serviceImpl.setOrders((ds.inventoryManagementSystem.grpc.generated.SetOrderRequest) request,
              (io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetOrderResponse>) responseObserver);
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

  private static abstract class SetOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SetOrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.inventoryManagementSystem.grpc.generated.InventoryManagementServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SetOrderService");
    }
  }

  private static final class SetOrderServiceFileDescriptorSupplier
      extends SetOrderServiceBaseDescriptorSupplier {
    SetOrderServiceFileDescriptorSupplier() {}
  }

  private static final class SetOrderServiceMethodDescriptorSupplier
      extends SetOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SetOrderServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SetOrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SetOrderServiceFileDescriptorSupplier())
              .addMethod(getSetOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
