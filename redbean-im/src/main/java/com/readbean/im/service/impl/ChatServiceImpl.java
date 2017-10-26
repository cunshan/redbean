package com.readbean.im.service.impl;

import com.readbean.im.service.ChatService;
import com.readbean.im.vo.ImMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

  private static final String DESTINATION = "/exchange/friend/";

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void sendToFriend(ImMessage message) {
    log.info(DESTINATION + message.getToUserId());
    simpMessagingTemplate.convertAndSend(DESTINATION + message.getToUserId(), message);
  }
}
