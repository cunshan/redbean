package com.readbean.im.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;//ID

  private String loginAccount;//登陆账号

  private String password;//密码

  private String name;//名称

  private String avatar = "/img/logo.jpg";//头像

  private Date createDate;
  private Date updateDate;
  private String createBy;
  private String updateBy;
}
