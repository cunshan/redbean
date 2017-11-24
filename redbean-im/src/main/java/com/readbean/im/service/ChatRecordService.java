package com.readbean.im.service;

import com.readbean.im.common.Pager;
import com.readbean.im.domain.ChatLog;

public interface ChatRecordService {
  Pager<ChatLog> findPage(Pager<ChatLog> pager, ChatLog chatLog);
}
