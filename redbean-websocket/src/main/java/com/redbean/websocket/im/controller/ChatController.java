package com.redbean.websocket.im.controller;

import com.redbean.websocket.im.vo.layim.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/friend")
  public void friendChat(@Header String user, UserMessage message) {

    messagingTemplate.convertAndSend("/exchange/friend/" + user, message);

  }

  @MessageMapping("/group")
  public void groupChat(@Header String group, UserMessage message) {

    messagingTemplate.convertAndSend("/exchange/group/" + group, message);

  }

}
