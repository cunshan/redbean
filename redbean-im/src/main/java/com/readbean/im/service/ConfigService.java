package com.readbean.im.service;

import com.readbean.im.domain.User;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.UserVo;
import java.util.List;

public interface ConfigService {

  /**.
   * 获取im初始化参数
   * @param loginAccount 登录账户
   */
  ImResponse init(String loginAccount);

  /**.
   * 获取好友列表
   */
  ImResponse<List<UserVo>> getAllFriends(String loginAccount);
}
