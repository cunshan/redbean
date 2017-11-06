package com.readbean.im.controller;

import com.readbean.im.service.ChatService;
import com.readbean.im.util.WebUtils;
import com.readbean.im.vo.ImMessage;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/rest")
@Slf4j
public class ChatRestController {

  @Resource
  private ChatService chatService;

  @PostMapping("/chat-log")
  public List<ImMessage> chatLog(String id, String type, HttpServletRequest request) {
    return chatService.queryChatLogs(WebUtils.getUserIdFromSession(request),id, type);
  }

}
