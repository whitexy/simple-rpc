package com.cxy.rpc.interf.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cxy.rpc.bean.User;
import com.cxy.rpc.interf.Login;

/**
 * @desc 登录的实现
 * @author chenxy
 * @date createTime:May 16, 2017 11:17:34 PM
 */
public class LoginImpl implements Login {

  private static final Map<String, User> USER_INFO = new HashMap<String, User>();

  static {
    User user = new User("cxy", "12346");
    USER_INFO.put("cxy", user);
  }

  @Override
  public boolean login(User user) {
    if (null == user) {
      return false;
    }
    String name = user.getName();
    if (StringUtils.isBlank(name)) {
      return false;
    }
    if (!USER_INFO.containsKey(name)) {
      return false;
    }
    User userInfo = USER_INFO.get(name);
    String password = user.getPassword();
    if (StringUtils.isNotBlank(password) && password.equals(userInfo.getPassword())) {
      return true;
    }
    return false;
  }

}
