package com.cxy.rpc.interf;

import com.cxy.rpc.bean.User;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 16, 2017 11:12:50 PM
 */
public interface Login {
  /**
   * 用户登录，返回用户是否登录成功
   * 
   * @param user 登录的用户信息
   * @return 是否是正确的用户
   */
  boolean login(User user);

}
