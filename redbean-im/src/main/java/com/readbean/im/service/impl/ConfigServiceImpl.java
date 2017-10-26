package com.readbean.im.service.impl;

import com.readbean.im.service.ConfigService;
import com.readbean.im.vo.ChatGroup;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.InitData;
import com.readbean.im.vo.User;
import com.readbean.im.vo.UserGroup;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

  @Override
  public ImResponse init(String loginAccount) {
    ImResponse response = new ImResponse();
    InitData data = new InitData();
    //登录用户
    User mine = buildUser(loginAccount);
    data.setMine(mine);
    //好友列表
    List<UserGroup> userGroups = buildUserGroups(loginAccount);
    data.setFriend(userGroups);
    //群组列表
    List<ChatGroup> chatGroups = buildChatGroups();
    data.setGroup(chatGroups);
    response.setData(data);
    return response;
  }

  private List<ChatGroup> buildChatGroups() {
    List<ChatGroup> list = new ArrayList<>();
    ChatGroup chatGroup1 = new ChatGroup();
    chatGroup1.setId("1");
    chatGroup1.setGroupname("Group-1");
    ChatGroup chatGroup2 = new ChatGroup();
    chatGroup2.setId("2");
    chatGroup2.setGroupname("Group-2");
    list.add(chatGroup1);
    list.add(chatGroup2);
    return list;
  }

  private User buildUser(String loginAccount) {
    User user = new User();
    user.setId(loginAccount);
    user.setSign("我是登录人");
    user.setUsername(loginAccount);
    user.setStatus(User.STATUS_ONLINE);
    return user;
  }

  private List<User> buildFriends(String loginAccount) {
    List<User> list = new ArrayList<>();
    if ("1".equals(loginAccount)) {
      list.add(buildUser("2"));
      list.add(buildUser("3"));
    } else if ("2".equals(loginAccount)) {
      list.add(buildUser("1"));
      list.add(buildUser("3"));
    } else if ("3".equals(loginAccount)) {
      list.add(buildUser("1"));
      list.add(buildUser("2"));
    }
    return list;
  }

  private List<UserGroup> buildUserGroups(String loginAccount) {
    //好友
    List<User> friends = buildFriends(loginAccount);
    List<UserGroup> list = new ArrayList<>();
    UserGroup group1 = new UserGroup();
    group1.setId("1");
    group1.setGroupname("任务中心");
    group1.setList(friends);
    list.add(group1);

    UserGroup group2 = new UserGroup();
    group2.setId("1");
    group2.setGroupname("任务中心");
    group2.setList(friends);
    list.add(group2);

    return list;
  }
}
