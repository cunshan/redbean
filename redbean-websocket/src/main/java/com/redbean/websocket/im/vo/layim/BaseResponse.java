package com.redbean.websocket.im.vo.layim;

import com.redbean.websocket.im.vo.ImData;
import com.redbean.websocket.im.vo.ImResponse;
import java.io.Serializable;
import lombok.Data;

@Data
public class BaseResponse implements ImResponse, Serializable {

  private static final long serialVersionUID = -8174606677993136050L;

  private static final String CODE_SUCCESS = "0";
  private static final String CODE_ERROR = "1";

  private String code = "0";
  private String msg;
  private ImData data;

}
