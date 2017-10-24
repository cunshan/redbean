package com.redbean.websocket.im.service;

import com.redbean.websocket.im.vo.ImResponse;

/**
 * . IM初始化参数service，提供初始化参数的查询构建
 */
public interface ConfigService {

  ImResponse getInitConfig();

  ImResponse getGroupMembers(String groupId);

}
