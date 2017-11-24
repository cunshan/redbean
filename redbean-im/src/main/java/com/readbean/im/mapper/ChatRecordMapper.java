package com.readbean.im.mapper;

import com.readbean.im.common.Pager;
import com.readbean.im.domain.ChatLog;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatRecordMapper {
  Long findCount(@Param("entity") ChatLog chatLog);

  List<ChatLog> findPage(@Param("pager") Pager<ChatLog> pager, @Param("entity") ChatLog chatLog);
}
