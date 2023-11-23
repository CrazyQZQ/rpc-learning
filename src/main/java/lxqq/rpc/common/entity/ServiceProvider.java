package lxqq.rpc.common.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ServiceProvider {
    Map<String, Object> serviceMap = new HashMap<>();

    public void addService(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            serviceMap.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return serviceMap.get(interfaceName);
    }
}
