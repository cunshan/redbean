package com.redbean.websocket.im.vo.layim;

import com.redbean.websocket.im.vo.ImMessage;
import java.io.Serializable;
import lombok.Data;

@Data
public class BaseMessage implements ImMessage,Serializable {

  private static final long serialVersionUID = -7359747494487060342L;
  private String id;//消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
  private String type;//聊天类型，一般分friend和group两种，group即群聊
  private String content;//聊天内容

}
