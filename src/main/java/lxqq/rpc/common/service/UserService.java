package lxqq.rpc.common.service;

import lxqq.rpc.common.entity.User;

public interface UserService {
    User getByName(String name);
}
