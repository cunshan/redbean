package com.redbean.distributor.common.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BaseDomain implements Serializable {

  private static final long serialVersionUID = -4561667763074573998L;
  @Id
  private Long id;//ID
  @Column
  private String createdBy;
  @Column
  private String updatedBy;
  @Column
  private Date createdDate;
  @Column
  private Date updatedDate;

}
