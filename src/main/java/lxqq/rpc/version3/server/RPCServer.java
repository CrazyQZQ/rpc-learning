package lxqq.rpc.version3.server;

/**
 * rpc服务端接口
 */
public interface RPCServer {
    void start(int port);

    void stop();
}
