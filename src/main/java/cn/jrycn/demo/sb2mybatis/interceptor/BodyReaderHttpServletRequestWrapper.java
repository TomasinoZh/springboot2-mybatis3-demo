package cn.jrycn.demo.sb2mybatis.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
  private final static Logger logger =
      LoggerFactory.getLogger(BodyReaderHttpServletRequestWrapper.class);

  private final byte[] body;

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
    // 获取到请求体
//    logger.info("【过滤器】-【获取请求的body信息】");
    body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
  }

  // 重写方法
  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    // 节点流
    final ByteArrayInputStream bais = new ByteArrayInputStream(body);
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener listener) {
        // 监听
        logger.info("【拦截器】-【BodyReaderHttpServletRequestWrapper ReadListener:{}】", this);
      }

      @Override
      public int read() throws IOException {
        return bais.read();
      }
    };
  }
}
