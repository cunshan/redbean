package com.redbean.websocket.controller;

import com.redbean.websocket.bean.Greeting;
import com.redbean.websocket.bean.HelloMessage;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscribe")
@Slf4j
public class SubscribeController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @GetMapping("/index")
  public String index() {
    return "subscribe/index";
  }

  @GetMapping("/user")
  public String user() {
    return "subscribe/user";
  }


  @MessageMapping("/say")
  public void say(@Header String user, HelloMessage message) {
    log.info("11");
    messagingTemplate.convertAndSend("/chat/" + user + "/greetings",
        new Greeting("Hello " + message.getName() + "!"));
  }
}
