package com.redbean.websocket.controller;

import com.redbean.websocket.bean.Greeting;
import com.redbean.websocket.bean.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) {

    return new Greeting("Hello " + message.getName() + "!");
  }


}
