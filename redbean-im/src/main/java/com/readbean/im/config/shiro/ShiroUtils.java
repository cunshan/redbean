package com.readbean.im.config.shiro;

import com.readbean.im.domain.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {

  /**
   * . 获取登录用户所有信息
   */
  public static User getLoginUser() {
    return (User) SecurityUtils.getSubject().getPrincipal();
  }

}
