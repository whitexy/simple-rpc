package com.cxy.rpc.bean;

import java.io.Serializable;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 16, 2017 11:13:04 PM
 */
public class User implements Serializable {

  private static final long serialVersionUID = -4143882760276278987L;
  /** 登录名 */
  private String name;
  /** 登录密码 */
  private String password;

  public User() {}

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", password=" + password + "]";
  }
}
