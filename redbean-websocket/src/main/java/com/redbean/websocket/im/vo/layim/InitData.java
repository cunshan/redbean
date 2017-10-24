package com.redbean.websocket.im.vo.layim;

import com.redbean.websocket.im.vo.ImGroup;
import java.util.List;
import lombok.Data;

@Data
public class InitData extends BaseData {

  private static final long serialVersionUID = -4766423666391544834L;

  private User mine;//我的信息
  private List<ImGroup> friend;//好友列表
  private List<ImGroup> group;//群组列表

}
