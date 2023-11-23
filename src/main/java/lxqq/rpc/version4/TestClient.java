package lxqq.rpc.version4;

import lxqq.rpc.common.client.RPCClient;
import lxqq.rpc.common.client.RPCClientProxy;
import lxqq.rpc.common.entity.Blog;
import lxqq.rpc.common.entity.User;
import lxqq.rpc.common.service.BlogService;
import lxqq.rpc.common.service.UserService;
import lxqq.rpc.version3.proxy.ClientProxy;
import lxqq.rpc.version4.client.NettyRPCClient;

public class TestClient {
    public static void main(String[] args) {
        RPCClient client = new NettyRPCClient("127.0.0.1", 8888);
        RPCClientProxy proxy = new RPCClientProxy(client);

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
