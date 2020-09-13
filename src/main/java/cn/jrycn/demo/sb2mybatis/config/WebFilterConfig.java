package cn.jrycn.demo.sb2mybatis.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.jrycn.demo.sb2mybatis.interceptor.HttpServletResponseReplacedFilter;

@Configuration
public class WebFilterConfig {
  @Bean
  public FilterRegistrationBean httpServletResponseReplacedFilter() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();

    HttpServletResponseReplacedFilter httpServletResponseReplacedFilter =
        new HttpServletResponseReplacedFilter();
    registrationBean.setFilter(httpServletResponseReplacedFilter);

    List<String> urls = new ArrayList<>();
    urls.add("/user/*");
    registrationBean.setUrlPatterns(urls);

    return registrationBean;
  }
}
