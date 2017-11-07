package com.readbean.im.service.impl;

import com.readbean.im.domain.ChatLog;
import com.readbean.im.mapper.ChatLogMapper;
import com.readbean.im.repository.ChatLogRepository;
import com.readbean.im.service.ChatService;
import com.readbean.im.vo.ChatLogVo;
import com.readbean.im.vo.ImMessage;
import com.readbean.im.vo.ImResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
  private ChatLogMapper chatLogMapper;

  @Override
  public void sendToFriend(ImMessage message) {
    log.info(DESTINATION + message.getToUserId());
    simpMessagingTemplate.convertAndSend(DESTINATION + message.getToUserId(), message);
  }

  @Override
  public ImResponse<List<ChatLogVo>> queryChatLogs(String userId, String toUserId, String type) {
    ChatLog param = new ChatLog();
    param.setFromUserId(Long.parseLong(userId));
    param.setToUserId(Long.parseLong(toUserId));
    List<ChatLog> list = chatLogMapper.queryAllChatLogs(param);
    List<ChatLogVo> logVos = new ArrayList<>();
    list.forEach(chatLog -> {
      ChatLogVo vo = new ChatLogVo();
      vo.setTimestamp(chatLog.getCreatedDate().getTime());
      vo.setAvatar(chatLog.getAvatar());
      vo.setContent(chatLog.getContent());
      vo.setId(chatLog.getFromUserId());
      vo.setUsername(chatLog.getUserName());
      logVos.add(vo);
    });
    ImResponse<List<ChatLogVo>> response = new ImResponse<>();
    response.setData(logVos);
    return response;
  }
}
