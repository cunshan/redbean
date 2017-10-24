package com.redbean.websocket.im.controller;

import com.redbean.websocket.im.vo.layim.BaseResponse;
import com.redbean.websocket.im.vo.layim.ChatGroup;
import com.redbean.websocket.im.vo.ImGroup;
import com.redbean.websocket.im.vo.ImResponse;
import com.redbean.websocket.im.vo.layim.InitData;
import com.redbean.websocket.im.vo.layim.InitResponse;
import com.redbean.websocket.im.vo.layim.User;
import com.redbean.websocket.im.vo.layim.UserGroup;
import com.redbean.websocket.im.vo.layim.MembersData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class MainController {


  @GetMapping("/init")
  private ImResponse init() {
    InitResponse response = new InitResponse();

    InitData initData = new InitData();

    User mine = new User();
    mine.setId("8888");
    mine.setSign("不要打扰我");
    mine.setStatus(User.STATUS_ONLINE);
    mine.setUsername("张三");
    initData.setMine(mine);

    User user = new User();
    user.setId("1");
    user.setSign("啊啊啊");
    user.setStatus(User.STATUS_ONLINE);
    user.setUsername("李四");
    List<ImGroup> friend = new ArrayList<>();
    UserGroup userGroup = new UserGroup();
    userGroup.setGroupname("我们是一对");
    userGroup.setId("1");
    userGroup.setList(Collections.singletonList(user));
    friend.add(userGroup);
    initData.setFriend(friend);

    List<ImGroup> groups = new ArrayList<>();
    ChatGroup group = new ChatGroup();
    group.setGroupname("任务中心");
    group.setId("1");
    groups.add(group);
    group = new ChatGroup();
    group.setGroupname("控制中心");
    group.setId("2");
    groups.add(group);

    initData.setGroup(groups);

    response.setData(initData);

    return response;
  }


  @GetMapping("/members")
  private ImResponse members(String id) {
    BaseResponse response = new BaseResponse();
    MembersData membersData = new MembersData();

    if("1".equals(id)){
      List<User> users = new ArrayList<>();
      User user = new User();
      user.setId("1");
      user.setSign("额鹅鹅鹅");
      user.setStatus(User.STATUS_OFFLINE);
      user.setUsername("王五");
      users.add(user);
      membersData.setList(users);
    }

    response.setData(membersData);
    return response;

  }


}
