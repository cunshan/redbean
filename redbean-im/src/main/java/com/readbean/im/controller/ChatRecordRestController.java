package com.readbean.im.controller;

import com.readbean.im.common.Pager;
import com.readbean.im.domain.ChatLog;
import com.readbean.im.service.ChatRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat-record/rest")
public class ChatRecordRestController {
  @Autowired
  private ChatRecordService service;

  @RequestMapping("/page")
  public Pager<ChatLog> findPage(Pager<ChatLog> pager, ChatLog chatLog) {
    return this.service.findPage(pager, chatLog);
  }
}
