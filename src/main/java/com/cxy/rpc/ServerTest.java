package com.cxy.rpc;

import java.io.IOException;

import com.cxy.rpc.bean.RPC;
import com.cxy.rpc.interf.Login;
import com.cxy.rpc.interf.impl.LoginImpl;
import com.cxy.rpc.server.RPCServer;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 17, 2017 1:06:23 AM
 */
public class ServerTest {

  public static void main(String[] args) throws IOException {
    RPCServer server = RPC.getRPCServer(Login.class, new LoginImpl(), 8888);
    server.start();
  }
}
