package lxqq.rpc.version3.server;

import lxqq.rpc.common.entity.RPCRequest;
import lxqq.rpc.common.entity.RPCResponse;
import lxqq.rpc.common.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCqServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                // 接受信息
                Socket s = ss.accept();
                System.out.println("客户端:" + s.getInetAddress().getHostAddress() + "已连接到服务器");
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                RPCRequest rpcRequest = (RPCRequest) ois.readObject();
                System.out.println("接受到客户端消息：" + rpcRequest);
                Class<?> clazz = Class.forName(rpcRequest.getInterfaceName());
                UserServiceImpl userService = new UserServiceImpl();
//                Object o = clazz.newInstance();
                Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
                Object invoke = method.invoke(userService, rpcRequest.getParams());

                RPCResponse response = RPCResponse.success(invoke);
                oos.writeObject(response);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
