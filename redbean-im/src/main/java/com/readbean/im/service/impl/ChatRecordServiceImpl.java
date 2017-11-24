package com.readbean.im.service.impl;

import com.readbean.im.common.Pager;
import com.readbean.im.domain.ChatLog;
import com.readbean.im.mapper.ChatRecordMapper;
import com.readbean.im.service.ChatRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRecordServiceImpl implements ChatRecordService {

  @Autowired
  private ChatRecordMapper mapper;

  @Override
  public Pager<ChatLog> findPage(
      Pager<ChatLog> pager, ChatLog chatLog) {
    Long count = this.mapper.findCount(chatLog);
    pager.setCount(count);
    if (count != null && count > 0) {
      List<ChatLog> list = this.mapper.findPage(pager, chatLog);
      pager.setData(list);
    }
    return pager;
  }
}
