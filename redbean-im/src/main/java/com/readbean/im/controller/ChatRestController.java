package com.readbean.im.controller;

import com.alibaba.fastjson.JSON;
import com.readbean.im.config.shiro.ShiroUtils;
import com.readbean.im.domain.User;
import com.readbean.im.service.ChatService;
import com.readbean.im.util.WebUtils;
import com.readbean.im.vo.ChatLogVo;
import com.readbean.im.vo.ImMessage;
import com.readbean.im.vo.ImResponse;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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
  public ImResponse<List<ChatLogVo>> chatLog(String id, String type, HttpServletRequest request) {
    return chatService.queryChatLogs(Long.toString(ShiroUtils.getLoginUser().getId()), id, type);
  }

}
