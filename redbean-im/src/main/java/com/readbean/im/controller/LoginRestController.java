package com.readbean.im.controller;

import com.readbean.im.config.shiro.ShiroUtils;
import com.readbean.im.domain.LoginLog;
import com.readbean.im.domain.User;
import com.readbean.im.service.LogService;
import com.readbean.im.service.UserService;
import com.readbean.im.util.WebUtils;
import com.readbean.im.vo.ImResponse;
import com.readbean.im.vo.UserVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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

  @Autowired
  private LogService logService;

  @PostMapping("/login")
  public ImResponse login(String loginAccount, String password, HttpServletRequest request,
      HttpServletResponse response) {
    UsernamePasswordToken token = new UsernamePasswordToken(loginAccount, password);
    SecurityUtils.getSubject().login(token);
    ImResponse<UserVo> imResponse = new ImResponse<>();
    imResponse.setMsg("登录成功");
    User loginUser = ShiroUtils.getLoginUser();
    UserVo userVo = new UserVo();
    userVo.setId(Long.toString(loginUser.getId()));
    userVo.setLoginAccount(loginAccount);
    imResponse.setData(userVo);
    //保存登录日志
    saveLoginLog(request, loginUser);
    return imResponse;
  }


  /**
   * . 保存登录日志
   */
  private void saveLoginLog(HttpServletRequest request, User user) {
    LoginLog log = new LoginLog();
    log.setUserId(user.getId());
    log.setLoginIp(WebUtils.getRemoteHost(request));
    log.setLoginAccount(user.getLoginAccount());
    logService.addLoginLog(log);
  }


}
