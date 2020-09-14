package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "输入参数错误")
public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = -4361766875231476305L;

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }

}
