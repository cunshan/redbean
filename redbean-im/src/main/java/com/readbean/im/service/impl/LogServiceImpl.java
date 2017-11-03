package com.readbean.im.service.impl;

import com.readbean.im.domain.ChatLog;
import com.readbean.im.domain.LoginLog;
import com.readbean.im.repository.ChatLogRepository;
import com.readbean.im.repository.LoginLogRepository;
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

  @Resource
  private LoginLogRepository loginLogRepository;

  @Override
  public void addChatLog(ImMessage message) {
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

  @Override
  public void addLoginLog(LoginLog log) {
    log.setCreatedDate(new Date());
    log.setCreatedBy(log.getLoginAccount());
    log.setUpdatedBy(log.getLoginAccount());
    log.setUpdatedDate(new Date());
    loginLogRepository.save(log);
  }
}
