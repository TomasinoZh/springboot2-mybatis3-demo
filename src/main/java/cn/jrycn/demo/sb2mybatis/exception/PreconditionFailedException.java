package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "参数前置条件错误")
public class PreconditionFailedException extends RuntimeException {

  private static final long serialVersionUID = -2270906437348445040L;

  public PreconditionFailedException() {
    super();
  }

  public PreconditionFailedException(String message) {
    super(message);
  }

}
