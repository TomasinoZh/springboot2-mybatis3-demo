package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "未认证")
public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = 4580067954919422024L;

  public UnauthorizedException() {
    super();
  }

  public UnauthorizedException(String message) {
    super(message);
  }

}