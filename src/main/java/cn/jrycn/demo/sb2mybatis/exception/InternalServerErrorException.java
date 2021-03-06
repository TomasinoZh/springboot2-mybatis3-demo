package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "服务器错误")
public class InternalServerErrorException extends RuntimeException {

  private static final long serialVersionUID = -2309239158180232043L;

  public InternalServerErrorException() {
    super();
  }

  public InternalServerErrorException(String message) {
    super(message);
  }

}