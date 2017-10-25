package com.redbean.websocket.im.controller;

import com.redbean.websocket.im.service.ConfigService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class MainController {


  @Autowired
  ConfigService configService;

  @GetMapping("/init")
  private ImResponse init(String loginAccount) {
    return configService.getInitConfig(loginAccount);
  }


  @GetMapping("/members")
  private ImResponse members(String id) {
    return configService.getGroupMembers(id);
  }


}
