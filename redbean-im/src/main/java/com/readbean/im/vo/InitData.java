package com.readbean.im.vo;

import java.util.List;
import lombok.Data;

/**.
 * 针对layui的初始化参数
 */
@Data
public class InitData implements ImData {

  private User mine;//我的信息  登录人的信息
  private List<UserGroup> friend;//好友列表
  private List<ChatGroup> group;//群组列表

}
