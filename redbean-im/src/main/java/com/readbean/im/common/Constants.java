package com.readbean.im.common;

/**
 * . 常量类
 */
public class Constants {


  /**
   * . 点对点聊天mq的exchange名称
   */
  public static final String FRIEND_MQ_EXCHANGE_KEY = "friend.user";

  /**
   * . im 用户点对点聊天的destination路径
   */
  public static final String FRIEND_DESTINATION = "/exchange/" + FRIEND_MQ_EXCHANGE_KEY + "/";

  /**
   * . 点对点mq的queue名前缀
   */
  public static final String FRIEND_MQ_QUEUE_PREFIX = "user.";


}
