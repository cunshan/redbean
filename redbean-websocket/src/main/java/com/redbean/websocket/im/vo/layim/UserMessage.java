package com.redbean.websocket.im.vo.layim;

import java.util.Date;
import lombok.Data;

@Data
public class UserMessage extends BaseMessage {

  private String username;
  private String avatar;
  private int cid = 0;
  private boolean mine = false;
  private String fromId;
  private Long timestamp = new Date().getTime();

}
