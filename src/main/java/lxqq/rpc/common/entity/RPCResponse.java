package lxqq.rpc.common.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 上个例子中response传输的是User对象，显然在一个应用中我们不可能只传输一种类型的数据
 * 由此我们将传输对象抽象成为Object
 * Rpc需要经过网络传输，有可能失败，类似于http，引入状态码和状态信息表示服务调用成功还是失败
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 具体数据
     */
    private Object data;
    /**
     * 具体数据类型
     */
    private Class<?> dataType;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).dataType(data.getClass()).build();
    }


    public static RPCResponse fail(String message) {
        return RPCResponse.builder().code(500).message(message == null ? "服务调用失败" : message).build();
    }

}