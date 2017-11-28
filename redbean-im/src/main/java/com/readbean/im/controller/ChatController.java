package com.readbean.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

  @GetMapping("/chat-log")
  public String chatLog(){
   return "chat/chatLog";
  }

  @GetMapping("/find")
  public String find(){
    return "chat/find";
  }

  @GetMapping("/msg-box")
  public String msgBox(){
    return "chat/msgBox";
  }
}
