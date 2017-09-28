package com.redbean.websocket.controller;

import com.redbean.websocket.bean.ChatMessage;
import com.redbean.websocket.bean.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * . websocket广播实例
 */
@Slf4j
@Controller
@RequestMapping("/broadcast")
public class BroadcastController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(ChatMessage message) {
    return new Greeting("", "Hello " + message.getName() + "!");
  }


  @GetMapping("/index")
  public String index() {
    return "broadcast/index";
  }

  @GetMapping("/user")
  public String user() {
    return "broadcast/user";
  }

}
