package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "接口未实现")
public class NotImplementedException extends RuntimeException {

  private static final long serialVersionUID = 5962638563002022863L;

  public NotImplementedException() {
    super();
  }

  public NotImplementedException(String message) {
    super(message);
  }

}
