package com.readbean.im.service;

import com.readbean.im.domain.User;
import java.util.List;

public interface UserService {

  User login(User user);

  /**
   * . 获取所有用户
   */
  List<User> getAllUserList();

  /**.
   * 根据ID 获取用户信息
   */
  User getUserById(Long userId);
}
