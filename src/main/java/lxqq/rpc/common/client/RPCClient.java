package lxqq.rpc.common.client;

import lxqq.rpc.common.entity.RPCRequest;
import lxqq.rpc.common.entity.RPCResponse;

public interface RPCClient {
    RPCResponse send(RPCRequest request);
}
