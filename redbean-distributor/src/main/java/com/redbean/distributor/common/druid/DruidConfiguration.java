package com.redbean.distributor.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.datasource.type",havingValue = "com.alibaba.druid.pool.DruidDataSource")
public class DruidConfiguration {

  @Bean
  public FilterRegistrationBean druidFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/druid");
    bean.addUrlPatterns("/*");
    log.info("Druid filter 配置完毕");
    return bean;
  }


  @Bean
  public ServletRegistrationBean statViewServlet(){
    ServletRegistrationBean bean = new ServletRegistrationBean();
    bean.setServlet(new StatViewServlet());
    bean.addUrlMappings("/druid/*");
    //允许清空统计数据
    bean.addInitParameter("resetEnable","true");
    //用户名
    bean.addInitParameter("loginUsername","druid");
    //密码
    bean.addInitParameter("loginPassword","druid");
    log.info("Druid StatView 配置完毕");
    return bean;
  }




}
