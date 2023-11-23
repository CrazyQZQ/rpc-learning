package lxqq.rpc.version3.server;

import lxqq.rpc.common.service.BlogService;
import lxqq.rpc.common.service.UserService;
import lxqq.rpc.common.service.impl.BlogServiceImpl;
import lxqq.rpc.common.service.impl.UserServiceImpl;
import lxqq.rpc.version3.entity.ServiceProvide;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvide serviceProvide = new ServiceProvide();
        serviceProvide.addService(userService);
        serviceProvide.addService(blogService);
        SimpleRPCRPCServer server = new SimpleRPCRPCServer(serviceProvide);
        server.start(8888);
    }
}
