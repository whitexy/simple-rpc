package com.cxy.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cxy.rpc.bean.Invocation;
import com.cxy.rpc.util.CloseUtil;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 17, 2017 12:23:21 AM
 */
public class RPCServer {
  /** Server端的socket实例 */
  private ServerSocket server;
  /** 启动的侦听端口 */
  private int port;
  /** Server端 RPC协议接口的实现缓存, 一个接口对应一个实现类的实例 */
  private static Map<Class<?>, Object> intfaceImpls = new HashMap<Class<?>, Object>();
  /** 处理 RPC请求的线程池 */
  private ExecutorService threadPool;

  /**
   * 构造一个 RPC 的 Server端实例
   * 
   * @param intface RPC协议接口的 Class对象
   * @param intfaceImpl Server端 RPC协议接口的实现
   * @param port Server端监听的端口
   */
  public <T> RPCServer(Class<T> intface, T intfaceImpl, int port) throws IOException {
    server = new ServerSocket(port);
    this.port = port;
    RPCServer.intfaceImpls.put(intface, intfaceImpl);
  }

  public void start() throws IOException {
    System.out.print("rpc server started.port:" + port);
    threadPool = Executors.newCachedThreadPool();
    try {
      while (true) {
        threadPool.execute(new RequestHandler(server.accept()));
      }
    } finally {
      threadPool.shutdownNow();
      CloseUtil.closeAll(server);
    }
  }

  public class RequestHandler implements Runnable {
    /** server 端接收到的客户端socket */
    private Socket client;
    /** 用于接收 client 的 RPC请求的输入流 */
    private ObjectInputStream ois;
    /** 用于向 client 返回 RPC请求结果的输出流 */
    private ObjectOutputStream oos;
    /** RPC请求的封装 */
    private Invocation invocation;

    public RequestHandler(Socket socket) {
      this.client = socket;
    }

    @Override
    public void run() {
      try {
        ois = new ObjectInputStream(client.getInputStream());
        invocation = (Invocation) ois.readObject();
        // 获取接口的实现类
        Object intfaceImpl = intfaceImpls.get(invocation.getInterf());
        Method method =
            intfaceImpl.getClass()
                .getMethod(invocation.getMethodName(), invocation.getParamTypes());
        method.setAccessible(true);
        Object res = method.invoke(intfaceImpl, invocation.getParams());

        oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(res);
        oos.flush();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        CloseUtil.closeAll(ois, oos);
        CloseUtil.closeAll(client);
      }
    }
  }

}
