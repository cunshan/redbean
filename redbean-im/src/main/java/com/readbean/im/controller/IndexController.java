package com.readbean.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


  @GetMapping("/")
  public String portal(){
    return "login";
  }

  @GetMapping("/chat-record")
  public String chatRecord(){
    return "chat/chat-record";
  }

  @GetMapping("/manage")
  public String manage(){
    return "chat/chat-record";
  }

  @GetMapping("/login")
  public String login(){
    return "login";
  }


  @GetMapping("/index")
  public String index(){
    return "index";
  }

}
