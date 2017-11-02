package com.readbean.im.controller;

import com.readbean.im.domain.User;
import com.readbean.im.service.UserService;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@Slf4j
public class LoginRestController {


  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ImResponse login(String loginAccount, String password) {
    User user = new User();
    user.setLoginAccount(loginAccount);
    user.setPassword(password);
    User loginUser = userService.login(user);
    ImResponse<UserVo> imResponse = new ImResponse<>();
    if (loginUser == null) {
      imResponse.setCode(ImResponse.CODE_ERROR);
      imResponse.setMsg("用户名或者密码错误！");
      return imResponse;
    }
    imResponse.setMsg("登录成功");
    UserVo userVo = new UserVo();
    userVo.setId(Long.toString(loginUser.getId()));
    userVo.setLoginAccount(loginAccount);
    imResponse.setData(userVo);
    return imResponse;
  }

}
