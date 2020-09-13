package cn.jrycn.demo.sb2mybatis.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServletResponseReplacedFilter implements Filter {

  private final static Logger logger =
      LoggerFactory.getLogger(HttpServletResponseReplacedFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    BodyWriteHttpServletResponseWrapper responseWrapper =
        new BodyWriteHttpServletResponseWrapper((HttpServletResponse) response);

    chain.doFilter(request, responseWrapper);

    // 获取response返回的内容并重新写入response
    String result = responseWrapper.getResponseData(response.getCharacterEncoding());
    response.getOutputStream().write(result.getBytes());

    logger.info("【过滤器】- {} {} {} {}", httpRequest.getMethod(), responseWrapper.getStatus(),
        httpRequest.getRequestURI(),
        result == null || result.length() == 0 ? "" : "\nResponse Body = " + result);
  }

  @Override
  public void destroy() {}

}
