package ds.inventoryManagementSystem.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: InventoryManagementServer.proto")
public final class SetProductQuantityServiceGrpc {

  private SetProductQuantityServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.inventoryManagementSystem.grpc.generated.SetProductQuantityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest,
      ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> getSetProductQuantityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setProductQuantity",
      requestType = ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest.class,
      responseType = ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest,
      ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> getSetProductQuantityMethod() {
    io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest, ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> getSetProductQuantityMethod;
    if ((getSetProductQuantityMethod = SetProductQuantityServiceGrpc.getSetProductQuantityMethod) == null) {
      synchronized (SetProductQuantityServiceGrpc.class) {
        if ((getSetProductQuantityMethod = SetProductQuantityServiceGrpc.getSetProductQuantityMethod) == null) {
          SetProductQuantityServiceGrpc.getSetProductQuantityMethod = getSetProductQuantityMethod =
              io.grpc.MethodDescriptor.<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest, ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setProductQuantity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SetProductQuantityServiceMethodDescriptorSupplier("setProductQuantity"))
              .build();
        }
      }
    }
    return getSetProductQuantityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SetProductQuantityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceStub>() {
        @java.lang.Override
        public SetProductQuantityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetProductQuantityServiceStub(channel, callOptions);
        }
      };
    return SetProductQuantityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SetProductQuantityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceBlockingStub>() {
        @java.lang.Override
        public SetProductQuantityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetProductQuantityServiceBlockingStub(channel, callOptions);
        }
      };
    return SetProductQuantityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SetProductQuantityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetProductQuantityServiceFutureStub>() {
        @java.lang.Override
        public SetProductQuantityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetProductQuantityServiceFutureStub(channel, callOptions);
        }
      };
    return SetProductQuantityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SetProductQuantityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void setProductQuantity(ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetProductQuantityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetProductQuantityMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest,
                ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse>(
                  this, METHODID_SET_PRODUCT_QUANTITY)))
          .build();
    }
  }

  /**
   */
  public static final class SetProductQuantityServiceStub extends io.grpc.stub.AbstractAsyncStub<SetProductQuantityServiceStub> {
    private SetProductQuantityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetProductQuantityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetProductQuantityServiceStub(channel, callOptions);
    }

    /**
     */
    public void setProductQuantity(ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetProductQuantityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SetProductQuantityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SetProductQuantityServiceBlockingStub> {
    private SetProductQuantityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetProductQuantityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetProductQuantityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse setProductQuantity(ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetProductQuantityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SetProductQuantityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SetProductQuantityServiceFutureStub> {
    private SetProductQuantityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetProductQuantityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetProductQuantityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> setProductQuantity(
        ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetProductQuantityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_PRODUCT_QUANTITY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SetProductQuantityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SetProductQuantityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_PRODUCT_QUANTITY:
          serviceImpl.setProductQuantity((ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest) request,
              (io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse>) responseObserver);
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

  private static abstract class SetProductQuantityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SetProductQuantityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.inventoryManagementSystem.grpc.generated.InventoryManagementServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SetProductQuantityService");
    }
  }

  private static final class SetProductQuantityServiceFileDescriptorSupplier
      extends SetProductQuantityServiceBaseDescriptorSupplier {
    SetProductQuantityServiceFileDescriptorSupplier() {}
  }

  private static final class SetProductQuantityServiceMethodDescriptorSupplier
      extends SetProductQuantityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SetProductQuantityServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SetProductQuantityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SetProductQuantityServiceFileDescriptorSupplier())
              .addMethod(getSetProductQuantityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
