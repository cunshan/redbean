package com.readbean.im.service;

import com.readbean.im.vo.ImMessage;
import java.util.List;

public interface ChatService {

  /**
   * . 发送给朋友的信息
   */
  void sendToFriend(ImMessage message);

  /**
   * . 获取聊天记录
   */
  List<ImMessage> queryChatLogs(String userId, String toUserId, String type);

}
