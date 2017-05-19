package com.cxy.rpc.bean;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.UnknownHostException;

import com.cxy.rpc.server.RPCServer;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 17, 2017 1:04:18 AM
 */
public class RPC {
  /**
   * 获取一个 Client 端的代理对象
   * 
   * @param intface RPC协议接口, Client 与 Server 端共同遵守
   * @param serverAdd Server 端地址
   * @param serverPort Server 端监听的端口
   * @return Client 端的代理对象
   */
  public static <T> Object getProxy(final Class<T> intface, String serverAdd, int serverPort)
      throws UnknownHostException, IOException {

    Object proxy =
        Proxy.newProxyInstance(intface.getClassLoader(), new Class[] {intface}, new Invoker(
            intface, serverAdd, serverPort));
    return proxy;
  }

  /**
   * 获取 RPC 的 Server 端实例
   * 
   * @param intface RPC协议接口
   * @param intfaceImpl Server 端 RPC协议接口的实现
   * @param port Server 端监听的端口
   * @return RPCServer 实例
   */
  public static <T> RPCServer getRPCServer(Class<T> intface, T intfaceImpl, int port)
      throws IOException {
    return new RPCServer(intface, intfaceImpl, port);
  }

}
