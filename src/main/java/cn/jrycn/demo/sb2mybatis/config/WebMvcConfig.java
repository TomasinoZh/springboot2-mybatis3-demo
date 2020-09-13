package cn.jrycn.demo.sb2mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cn.jrycn.demo.sb2mybatis.interceptor.RequestInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private RequestInterceptor requestInterceptor;

  // 配置拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 指定拦截器，指定拦截路径
    registry.addInterceptor(requestInterceptor).addPathPatterns("/user/**");
  }

}
