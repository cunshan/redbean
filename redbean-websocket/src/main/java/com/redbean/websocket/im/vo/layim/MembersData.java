package com.redbean.websocket.im.vo.layim;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MembersData extends BaseData{

  private List<User> list = new ArrayList<>();

}
