package com.cxy.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import com.cxy.rpc.bean.RPC;
import com.cxy.rpc.bean.User;
import com.cxy.rpc.interf.Login;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 17, 2017 1:06:05 AM
 */
public class ClientTest {

  public static void main(String[] args) throws UnknownHostException, IOException {
    // 获取一个 Client端的代理对象 proxy
    Login proxy = (Login) RPC.getProxy(Login.class, "192.168.109.128", 8888);
    User user = new User("cxy", "123462");
    boolean success = proxy.login(user);
    System.out.println(success ? "恭喜您，成功登录！" : "对不起，您登录失败！");
  }
}
