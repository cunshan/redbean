package com.readbean.im.service.impl;

import com.readbean.im.domain.User;
import com.readbean.im.repository.UserGroupRepository;
import com.readbean.im.repository.UserRepository;
import com.readbean.im.service.ConfigService;
import com.readbean.im.vo.ChatGroupVo;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.InitData;
import com.readbean.im.vo.UserVo;
import com.readbean.im.vo.UserGroupVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserGroupRepository userGroupRepository;

  @Override
  public ImResponse init(String loginAccount) {
    ImResponse<InitData> response = new ImResponse<>();
    InitData data = new InitData();
    //登录用户
    data.setMine(buildUser(loginAccount));
    //好友列表
    List<UserGroupVo> userGroups = buildUserGroups(loginAccount);
    data.setFriend(userGroups);
    //群组列表
    List<ChatGroupVo> chatGroups = buildChatGroups();
    data.setGroup(chatGroups);
    response.setData(data);
    return response;
  }

  @Override
  public ImResponse<List<UserVo>> getAllFriends(String loginAccount) {
    List<UserVo> userList = new ArrayList<>();
    userRepository.findAll().forEach(user -> userList.add(buildUserVoFromUser(user)));
    ImResponse<List<UserVo>> response = new ImResponse<>();
    response.setData(userList);
    return response;
  }

  private List<ChatGroupVo> buildChatGroups() {
    List<ChatGroupVo> list = new ArrayList<>();
    ChatGroupVo chatGroup1 = new ChatGroupVo();
    chatGroup1.setId("1");
    chatGroup1.setGroupname("Group-1");
    ChatGroupVo chatGroup2 = new ChatGroupVo();
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
    userVo.setId(Long.toString(loginUser.getId()));
    userVo.setUsername(loginUser.getName());
    userVo.setStatus(UserVo.STATUS_ONLINE);
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

  private List<UserGroupVo> buildUserGroups(String loginAccount) {
    //好友
    List<UserVo> friends = buildFriends(loginAccount);
    List<UserGroupVo> list = new ArrayList<>();
    userGroupRepository.findAll().forEach(userGroup -> {
      UserGroupVo vo = new UserGroupVo();
      vo.setGroupname(userGroup.getGroupName());
      vo.setId(userGroup.getId().toString());
      vo.setList(friends);
      list.add(vo);
    });

    return list;
  }
}
