package com.readbean.im.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Pager<T> implements Serializable {
  private Integer code = 0;
  private String msg;
  private Long count;
  private List<T> data;

  private Long page = 1L;
  private Long limit = 10L;

  private Long getStart() {
    return (this.page - 1) * this.limit;
  }

}
