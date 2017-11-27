package com.readbean.im.config;

import com.readbean.im.common.Constants;
import com.readbean.im.domain.User;
import com.readbean.im.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * . 项目启动初始化所有bean之后，自动创建聊天要用的队列 1、创建exchange 2、查询出所有用户 3、为每个用户建立queue 4、建立binding
 */
@Configuration
@Slf4j
public class ApplicationStartConfig implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    AmqpAdmin amqpAdmin = event.getApplicationContext().getBean(AmqpAdmin.class);
    //创建exchange
    Exchange exchange = new DirectExchange(Constants.FRIEND_MQ_EXCHANGE_KEY);
    amqpAdmin.declareExchange(exchange);
    //查询出所有用户
    UserService userService = event.getApplicationContext().getBean(UserService.class);
    List<User> userList = userService.getAllUserList();
    userList.forEach(user -> {
      //为每个用户建立queue
      Queue queue = new Queue(Constants.FRIEND_MQ_QUEUE_PREFIX + user.getId().toString());
      amqpAdmin.declareQueue(queue);
      //建立binding
      Binding binding = BindingBuilder.bind(queue).to(exchange).with(user.getId().toString())
          .noargs();
      amqpAdmin.declareBinding(binding);
    });
    log.info("初始化用户队列完成。。。");
  }
}
