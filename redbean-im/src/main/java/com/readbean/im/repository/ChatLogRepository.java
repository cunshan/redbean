package com.readbean.im.repository;

import com.readbean.im.domain.ChatLog;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ChatLogRepository extends CrudRepository<ChatLog,Long>{

  List<ChatLog> findAllByFromUserIdAndToUserId(Long fromUserId,Long toUserId);

}
