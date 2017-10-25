package com.redbean.websocket.im.vo.layim;

import lombok.Data;

@Data
public class SystemMessage extends BaseMessage {

  private static final long serialVersionUID = -6637565611955306657L;
  private boolean system = true;//系统消息

}
