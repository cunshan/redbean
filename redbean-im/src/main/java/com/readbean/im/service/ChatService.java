package com.readbean.im.service;

import com.readbean.im.vo.ImMessage;

public interface ChatService {

  /**.
   * 发送给朋友的信息
   */
  void sendToFriend(ImMessage message);
}
