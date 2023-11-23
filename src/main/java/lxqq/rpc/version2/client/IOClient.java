package lxqq.rpc.version2.client;


import lxqq.rpc.common.entity.RPCRequest;
import lxqq.rpc.common.entity.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * IOClient类用于进行客户端的输入输出操作
 */
public class IOClient {
    /**
     * send方法用于向指定的主机和端口发送RPC请求并获取响应数据
     *
     * @param host    主机名
     * @param port    端口号
     * @param request RPC请求对象
     * @return 响应数据
     */
    public static Object send(String host, int port, RPCRequest request) {
        try {
            // 创建Socket对象并连接到指定的主机和端口
            Socket s = new Socket(host, port);
            // 创建ObjectOutputStream对象用于向输出流发送对象
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            // 创建ObjectInputStream对象用于从输入流接收对象
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            // 将RPC请求对象序列化并发送给服务器端
            oos.writeObject(request);
            oos.flush();
            // 从服务器端接收响应对象，并将其转换为RPCResponse类型
            RPCResponse response = (RPCResponse) ois.readObject();
            // 打印服务器端返回的消息
            System.out.println("接受到服务端消息：" + response);
            // 返回响应数据
            return response.getData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 如果发生异常，则返回null
        return null;
    }
}

