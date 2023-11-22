package lxqq.rpc.common.service.impl;

import lxqq.rpc.common.entity.User;
import lxqq.rpc.common.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getByName(String name) {
        System.out.println("getByName入参name：" + name);
        return User.builder().name("qq").password("123").age("18").build();
    }
}
