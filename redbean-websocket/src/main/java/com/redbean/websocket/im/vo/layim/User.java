package com.redbean.websocket.im.vo.layim;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {

  private static final long serialVersionUID = -8445560376151430535L;

  public static final String STATUS_OFFLINE = "offline";
  public static final String STATUS_ONLINE = "online";

  private String id;//ID
  private String username;//昵称
  private String status;//在线状态 online：在线、hide：隐身
  private String sign;//我的签名
  private String avatar = "/img/logo.jpg";//头像

}
