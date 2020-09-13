package cn.jrycn.demo.sb2mybatis.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServletRequestReplacedFilter implements Filter {

  private final static Logger logger =
      LoggerFactory.getLogger(HttpServletRequestReplacedFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // logger.info("【过滤器】-【已进入过滤器！】");
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpRequest);

    chain.doFilter(requestWrapper, response);
  }

  @Override
  public void destroy() {}
}
