package ds.inventoryManagementSystem.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: InventoryManagementServer.proto")
public final class CheckProductQuantityServiceGrpc {

  private CheckProductQuantityServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest,
      ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> getCheckProductQuantityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkProductQuantity",
      requestType = ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest.class,
      responseType = ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest,
      ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> getCheckProductQuantityMethod() {
    io.grpc.MethodDescriptor<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest, ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> getCheckProductQuantityMethod;
    if ((getCheckProductQuantityMethod = CheckProductQuantityServiceGrpc.getCheckProductQuantityMethod) == null) {
      synchronized (CheckProductQuantityServiceGrpc.class) {
        if ((getCheckProductQuantityMethod = CheckProductQuantityServiceGrpc.getCheckProductQuantityMethod) == null) {
          CheckProductQuantityServiceGrpc.getCheckProductQuantityMethod = getCheckProductQuantityMethod =
              io.grpc.MethodDescriptor.<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest, ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkProductQuantity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckProductQuantityServiceMethodDescriptorSupplier("checkProductQuantity"))
              .build();
        }
      }
    }
    return getCheckProductQuantityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckProductQuantityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceStub>() {
        @java.lang.Override
        public CheckProductQuantityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckProductQuantityServiceStub(channel, callOptions);
        }
      };
    return CheckProductQuantityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckProductQuantityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceBlockingStub>() {
        @java.lang.Override
        public CheckProductQuantityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckProductQuantityServiceBlockingStub(channel, callOptions);
        }
      };
    return CheckProductQuantityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckProductQuantityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckProductQuantityServiceFutureStub>() {
        @java.lang.Override
        public CheckProductQuantityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckProductQuantityServiceFutureStub(channel, callOptions);
        }
      };
    return CheckProductQuantityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CheckProductQuantityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkProductQuantity(ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckProductQuantityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckProductQuantityMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest,
                ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse>(
                  this, METHODID_CHECK_PRODUCT_QUANTITY)))
          .build();
    }
  }

  /**
   */
  public static final class CheckProductQuantityServiceStub extends io.grpc.stub.AbstractAsyncStub<CheckProductQuantityServiceStub> {
    private CheckProductQuantityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckProductQuantityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckProductQuantityServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkProductQuantity(ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest request,
        io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckProductQuantityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckProductQuantityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CheckProductQuantityServiceBlockingStub> {
    private CheckProductQuantityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckProductQuantityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckProductQuantityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse checkProductQuantity(ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckProductQuantityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckProductQuantityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CheckProductQuantityServiceFutureStub> {
    private CheckProductQuantityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckProductQuantityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckProductQuantityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> checkProductQuantity(
        ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckProductQuantityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_PRODUCT_QUANTITY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckProductQuantityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckProductQuantityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_PRODUCT_QUANTITY:
          serviceImpl.checkProductQuantity((ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest) request,
              (io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse>) responseObserver);
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

  private static abstract class CheckProductQuantityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckProductQuantityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.inventoryManagementSystem.grpc.generated.InventoryManagementServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CheckProductQuantityService");
    }
  }

  private static final class CheckProductQuantityServiceFileDescriptorSupplier
      extends CheckProductQuantityServiceBaseDescriptorSupplier {
    CheckProductQuantityServiceFileDescriptorSupplier() {}
  }

  private static final class CheckProductQuantityServiceMethodDescriptorSupplier
      extends CheckProductQuantityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckProductQuantityServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CheckProductQuantityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckProductQuantityServiceFileDescriptorSupplier())
              .addMethod(getCheckProductQuantityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
