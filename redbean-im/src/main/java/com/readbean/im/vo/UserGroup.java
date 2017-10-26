package com.readbean.im.vo;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class UserGroup implements Serializable{

  private static final long serialVersionUID = -1307062978485853714L;
  private String id;//分组ID
  private String groupname;//好友分组名
  private List<User> list;//分组下的好友列表

}
