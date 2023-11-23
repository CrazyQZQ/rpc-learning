package lxqq.rpc.version3.server;

import lombok.AllArgsConstructor;
import lxqq.rpc.common.entity.ServiceProvider;
import lxqq.rpc.common.server.RPCServer;
import lxqq.rpc.version3.Thread.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCRPCServer implements RPCServer {
    /**
     * 服务对象
     */
    ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("启动服务器....");
            while (true) {
                // 接受信息
                Socket s = ss.accept();
                // 启用线程去处理
                new Thread(new WorkThread(s, serviceProvider.getServiceMap())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
