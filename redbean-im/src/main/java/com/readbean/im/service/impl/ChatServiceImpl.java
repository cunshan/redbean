package com.readbean.im.service.impl;

import com.readbean.im.repository.ChatLogRepository;
import com.readbean.im.service.ChatService;
import com.readbean.im.vo.ImMessage;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

  private static final String DESTINATION = "/queue/user-";

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  private ChatLogRepository chatLogRepository;

  @Override
  public void sendToFriend(ImMessage message) {
    log.info(DESTINATION + message.getToUserId());
    simpMessagingTemplate.convertAndSend(DESTINATION + message.getToUserId(), message);
  }

  @Override
  public List<ImMessage> queryChatLogs(String userId, String toUserId, String type) {

    return null;
  }
}
