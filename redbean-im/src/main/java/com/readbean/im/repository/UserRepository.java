package com.readbean.im.repository;

import com.readbean.im.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

  /**
   * 根据用户名和密码获取用户
   */
  User findDistinctByLoginAccountAndPassword(String loginAccount,String password);

  /**.
   * 根据用户名查询单个用户
   */
  User findDistinctByLoginAccount(String loginAccount);




}
