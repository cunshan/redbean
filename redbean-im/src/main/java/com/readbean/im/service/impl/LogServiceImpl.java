package com.readbean.im.service.impl;

import com.readbean.im.domain.ChatLog;
import com.readbean.im.repository.ChatLogRepository;
import com.readbean.im.service.LogService;
import com.readbean.im.vo.ImMessage;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {

  @Resource
  private ChatLogRepository chatLogRepository;

  @Override
  public void add(ImMessage message) {
    ChatLog log = new ChatLog();
    log.setContent(message.getContent());
    log.setFromUserId(Long.parseLong(message.getId()));
    log.setToUserId(Long.parseLong(message.getToUserId()));
    log.setCreatedBy(message.getId());
    log.setUpdatedBy(message.getId());
    log.setCreatedDate(new Date());
    log.setUpdatedDate(new Date());
    chatLogRepository.save(log);
  }
}
