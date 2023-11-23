package lxqq.rpc.version3.Thread;

import lxqq.rpc.common.entity.RPCRequest;
import lxqq.rpc.common.entity.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class WorkThread implements Runnable {
    private final Socket s;
    private final Map<String, Object> serviceMap;

    public WorkThread(Socket s, Map<String, Object> serviceMap) {
        this.s = s;
        this.serviceMap = serviceMap;
    }

    @Override
    public void run() {
        try {

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            RPCRequest rpcRequest = (RPCRequest) ois.readObject();
            System.out.println("接受到客户端消息：" + rpcRequest);
            Object serviceObj = serviceMap.get(rpcRequest.getInterfaceName());
            if (serviceObj == null) {
                oos.writeObject(RPCResponse.fail("服务不存在"));
                oos.flush();
                return;
            }
            Method method = serviceObj.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = method.invoke(serviceObj, rpcRequest.getParams());
            RPCResponse response = RPCResponse.success(invoke);
            oos.writeObject(response);
            oos.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
