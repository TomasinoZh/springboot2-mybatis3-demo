package cn.jrycn.demo.sb2mybatis.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodyWriteHttpServletResponseWrapper extends HttpServletResponseWrapper {
  
  private final static Logger logger =
      LoggerFactory.getLogger(BodyWriteHttpServletResponseWrapper.class);
  private ByteArrayOutputStream buffer = null;
  private ServletOutputStream out = null;
  private PrintWriter writer = null;

  public BodyWriteHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
    super(response);
    buffer = new ByteArrayOutputStream();
    out = new WapperedOutputStream(buffer);
    writer = new PrintWriter(new OutputStreamWriter(buffer, "UTF-8"));
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return out;
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return writer;
  }

  @Override
  public void flushBuffer() throws IOException {
    if (out != null) {
      out.flush();
    }
    if (writer != null) {
      writer.flush();
    }
  }

  @Override
  public void reset() {
    buffer.reset();
  }

  public String getResponseData(String charset) throws IOException {
    flushBuffer();
    byte[] bytes = buffer.toByteArray();
    try {
      return new String(bytes, charset);
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  class WapperedOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream bos = null;

    public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
      bos = stream;
    }

    @Override
    public void write(int b) throws IOException {
      bos.write(b);
    }

    @Override
    public boolean isReady() {
      return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
      // 监听
      logger.info("【拦截器】-【BodyWriteHttpServletResponseWrapper WriteListener:{}】", this);
    }
  }
}
