package com.redbean.websocket.im.vo.layim;

import com.redbean.websocket.im.vo.ImGroup;
import java.io.Serializable;
import lombok.Data;

@Data
public class BaseGroup implements ImGroup, Serializable {

  private static final long serialVersionUID = 7840675863451933411L;

  private String id;//群组ID
  private String groupname;//群组名

}
