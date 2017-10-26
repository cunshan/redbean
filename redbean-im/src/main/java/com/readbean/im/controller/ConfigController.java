package com.readbean.im.controller;

import com.readbean.im.service.ConfigService;
import com.readbean.im.vo.ImResponse;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

  @Resource
  private ConfigService configService;

  @GetMapping("/init")
  public ImResponse init(String loginAccount){
    return configService.init(loginAccount);
  }


  @GetMapping("/members")
  public void members(String loginAccount){

  }


}
