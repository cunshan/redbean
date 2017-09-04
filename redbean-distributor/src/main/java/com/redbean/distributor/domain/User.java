package com.redbean.distributor.domain;


import com.redbean.distributor.common.domain.BaseDomain;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseDomain{

  private static final long serialVersionUID = -2909421985220222620L;
  @Column
  private String loginAccount;
  @Column
  private String userName;
  @Column
  private String userPassword;
  @Column
  private Boolean locked;


}
