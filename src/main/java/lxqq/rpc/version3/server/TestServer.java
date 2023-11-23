package lxqq.rpc.version3.server;

import lxqq.rpc.common.entity.ServiceProvider;
import lxqq.rpc.common.service.BlogService;
import lxqq.rpc.common.service.UserService;
import lxqq.rpc.common.service.impl.BlogServiceImpl;
import lxqq.rpc.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.addService(userService);
        serviceProvider.addService(blogService);
        SimpleRPCRPCServer server = new SimpleRPCRPCServer(serviceProvider);
        server.start(8888);
    }
}
