package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "服务器暂时不可用")
public class ServiceUnavailableException extends RuntimeException {

  private static final long serialVersionUID = 6233330027573778789L;

  public ServiceUnavailableException() {
    super();
  }

  public ServiceUnavailableException(String message) {
    super(message);
  }

}