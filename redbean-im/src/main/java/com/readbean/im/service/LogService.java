package com.readbean.im.service;

import com.readbean.im.vo.ImMessage;

public interface LogService {

  /**.
   * 添加聊天日志
   */
  void add(ImMessage message);
}
