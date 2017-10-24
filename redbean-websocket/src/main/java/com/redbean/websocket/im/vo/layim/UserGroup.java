package com.redbean.websocket.im.vo.layim;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * . 用户分组对象
 */
@Data
public class UserGroup extends BaseGroup {

  private static final long serialVersionUID = 5326783522169660936L;

  private List<User> list = new ArrayList<>();//群组的用户列表

}
