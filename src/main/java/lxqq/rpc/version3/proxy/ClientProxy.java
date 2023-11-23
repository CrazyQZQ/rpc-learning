package lxqq.rpc.version3.proxy;

import lombok.AllArgsConstructor;
import lxqq.rpc.common.entity.RPCRequest;
import lxqq.rpc.version3.client.IOClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    // 主机地址
    private String host;
    // 端口号
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 构建 RPC 请求
        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        // 调用 IOClient 发送请求并返回结果
        return IOClient.send(host, port, request);
    }

    /**
     * 创建代理对象
     *
     * @param clazz 代理对象类型
     * @return 代理对象
     */
    public <T> T getProxy(Class<T> clazz) {
        // 使用 Proxy.newProxyInstance 创建代理对象
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) o;
    }
}

