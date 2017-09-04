package com.redbean.distributor.controller;

import com.redbean.distributor.domain.User;
import com.redbean.distributor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  @RequestMapping("/")
  public ModelAndView index(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }

}
