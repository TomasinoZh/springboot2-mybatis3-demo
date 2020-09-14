package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "没有权限执行此操作")
public class ForbiddenException extends RuntimeException {

  private static final long serialVersionUID = 4586854338114319223L;

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }

}
