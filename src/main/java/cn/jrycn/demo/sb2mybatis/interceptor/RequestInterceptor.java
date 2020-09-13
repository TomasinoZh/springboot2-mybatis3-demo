package cn.jrycn.demo.sb2mybatis.interceptor;

import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

  private final static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

  // 业务处理器处理请求之前被调用，对用户的request进行处理
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String headerString = this.getHeaderString(request);
    String parameterString = this.getParameterString(request);
    String cookieString = this.getCookieString(request);
    String queryString = request.getQueryString();
    String bodyString = this.getBodyString(request);
    logger.info("【拦截器】- {} {}{}{}{}{}{}", request.getMethod(), request.getRequestURI(),
        queryString == null ? "" : "?" + queryString, // URL中的参数
        headerString == null || headerString.length() == 0 ? "" : "\n" + headerString, // 头
        parameterString == null || parameterString.length() == 0 ? "" : "\n" + parameterString, // 解析后的参数
        cookieString == null || cookieString.length() == 0 ? "" : "\n" + cookieString, // cookie
        bodyString == null || bodyString.length() == 0 ? "" : "\n" + bodyString);// body

    // TODO Authentication 认证校验
    // TODO Authorization 权限校验

    return true;
  }

  private String getHeaderString(HttpServletRequest request) {
    String headerString = "";
    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String value = request.getHeader(name);
      headerString += "    " + name + ":\"" + value + "\";\n";
    }
    if (headerString.length() == 0) {
      return headerString;
    } else {
      return "Headers = [" + headerString + "]";
    }
  }

  private String getParameterString(HttpServletRequest request) {
    String parameterString = "";
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String value = request.getParameter(name);
      parameterString += "    " + name + "=[" + value + "], \n";
    }
    if (parameterString.length() == 0) {
      return parameterString;
    } else {
      return "Parameters = {" + parameterString + "}";
    }
  }

  private String getCookieString(HttpServletRequest request) {
    String cookieString = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookieString += "    " + cookie.getName() + "=" + cookie.getValue() + ";\n";
      }
    }
    if (cookieString.length() == 0) {
      return cookieString;
    } else {
      return "Cookies = [" + cookieString + "]";
    }
  }

  private String getBodyString(HttpServletRequest request) {
    String bodyString = HttpHelper.getBodyString(request);
    if (bodyString.length() == 0) {
      return bodyString;
    } else {
      return "Body = " + bodyString + "";
    }
  }

  @Bean
  public FilterRegistrationBean httpServletRequestReplacedRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new HttpServletRequestReplacedFilter());
    registration.addUrlPatterns("/*");
    registration.addInitParameter("paramName", "paramValue");
    registration.setName("httpServletRequestReplacedFilter");
    registration.setOrder(1);
    return registration;
  }
}
