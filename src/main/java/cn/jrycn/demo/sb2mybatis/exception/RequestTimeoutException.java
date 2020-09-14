package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "输入超时参数错误")
public class RequestTimeoutException extends RuntimeException {

  private static final long serialVersionUID = -1608336326760642114L;

  public RequestTimeoutException() {
    super();
  }

  public RequestTimeoutException(String message) {
    super(message);
  }

}
