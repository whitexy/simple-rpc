package com.cxy.rpc.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cxy.rpc.util.CloseUtil;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 16, 2017 11:54:25 PM
 */
public class Invoker implements InvocationHandler {
  /** RPC协议接口的 Class对象 */
  private Class<?> intface;
  /** Client 端 Socket */
  private Socket client;
  /** 用于向 Server端发送 RPC请求的输出流 */
  private ObjectOutputStream oos;
  /** 用于接收 Server端返回的 RPC请求结果的输入流 */
  private ObjectInputStream ois;

  public Invoker(Class<?> intface, String serverAdd, int port) throws UnknownHostException,
      IOException {
    this.intface = intface;
    client = new Socket(serverAdd, port);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      // 新建一个RPC封装的调用对象
      Invocation invocation =
          new Invocation(intface, method.getName(), args, method.getParameterTypes());
      oos = new ObjectOutputStream(client.getOutputStream());
      oos.writeObject(invocation);
      oos.flush();
      ois = new ObjectInputStream(client.getInputStream());
      Object res = ois.readObject();
      return res;
    } finally {
      CloseUtil.close(client);
      CloseUtil.closeAll(ois, oos);
    }
  }
}
