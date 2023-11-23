package lxqq.rpc.version3.client;

import lxqq.rpc.common.entity.Blog;
import lxqq.rpc.common.entity.User;
import lxqq.rpc.common.service.BlogService;
import lxqq.rpc.common.service.UserService;
import lxqq.rpc.version3.entity.RPCRequest;
import lxqq.rpc.version3.entity.RPCResponse;
import lxqq.rpc.version3.proxy.ClientProxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCClient {
    public static void main(String[] args) {
//        send();
        sendWithProxy();
    }

    static void send() {
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            RPCRequest rpcRequest = RPCRequest.builder()
                    .interfaceName("lxqq.rpc.common.service.impl.UserServiceImpl")
                    .methodName("getByName")
                    .params(new String[]{"qq"})
                    .paramsTypes(new Class[]{String.class})
                    .build();
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            oos.writeObject(rpcRequest);
            oos.flush();
            RPCResponse response = (RPCResponse) ois.readObject();
            System.out.println("接受到服务端消息：" + response);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void sendWithProxy() {
        // 创建一个ClientProxy对象，指定代理服务器的IP地址和端口号
        ClientProxy proxy = new ClientProxy("127.0.0.1", 8888);

        BlogService blogService = proxy.getProxy(BlogService.class);
        Blog blog = blogService.getBlogById(1);
        System.out.println("接受到服务端消息：" + blog);
        // 通过代理获取一个UserService接口的实例对象
        UserService userService = proxy.getProxy(UserService.class);
        // 通过UserService接口的实例对象获取名为"qq"的用户信息
        User user = userService.getByName("qq");
        // 打印接收到的用户信息
        System.out.println("接受到服务端消息：" + user);
    }

}
