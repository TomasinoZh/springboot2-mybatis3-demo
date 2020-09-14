package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "资源不存在")
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = 3090876514983152523L;

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }
}
