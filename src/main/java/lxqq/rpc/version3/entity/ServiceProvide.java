package lxqq.rpc.version3.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ServiceProvide {
    Map<String, Object> serviceMap = new HashMap<>();

    public void addService(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            serviceMap.put(clazz.getName(), service);
        }
    }
}
