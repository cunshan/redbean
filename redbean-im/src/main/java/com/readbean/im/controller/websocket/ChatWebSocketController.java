package com.readbean.im.controller.websocket;

import com.readbean.im.service.ChatService;
import com.readbean.im.vo.ImMessage;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatWebSocketController {


  @Resource
  private ChatService chatService;

  @MessageMapping("/talk-to-friend")
  public void friend(ImMessage message) {
    chatService.sendToFriend(message);
  }


  @MessageMapping("/group")
  public void group() {

  }



}
