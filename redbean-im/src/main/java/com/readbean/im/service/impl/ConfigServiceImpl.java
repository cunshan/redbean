package com.readbean.im.service.impl;

import com.readbean.im.domain.User;
import com.readbean.im.repository.UserRepository;
import com.readbean.im.service.ConfigService;
import com.readbean.im.vo.ChatGroup;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.InitData;
import com.readbean.im.vo.UserVo;
import com.readbean.im.vo.UserGroup;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public ImResponse init(String loginAccount) {
    ImResponse response = new ImResponse();
    InitData data = new InitData();
    //登录用户
    data.setMine(buildUser(loginAccount));
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

  private UserVo buildUser(String loginAccount) {
    User loginUser = userRepository.findDistinctByLoginAccount(loginAccount);
    return buildUserVoFromUser(loginUser);
  }

  private UserVo buildUserVoFromUser(User loginUser) {
    UserVo userVo = new UserVo();
    userVo.setAvatar(loginUser.getAvatar());
    userVo.setId(loginUser.getLoginAccount());
    userVo.setUsername(loginUser.getName());
    return userVo;
  }

  private List<UserVo> buildFriends(String loginAccount) {
    List<UserVo> list = new ArrayList<>();
    //TODO 应该从好友分类里查询列表，现在暂时查询所有好友
    userRepository.findAll().forEach(user -> {
      list.add(buildUserVoFromUser(user));
    });
    return list;
  }

  private List<UserGroup> buildUserGroups(String loginAccount) {
    //好友
    List<UserVo> friends = buildFriends(loginAccount);
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
