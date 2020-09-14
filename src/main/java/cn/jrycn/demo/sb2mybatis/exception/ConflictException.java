package cn.jrycn.demo.sb2mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 在自定义的异常上添加@ResponseStatus注解，将其映射为一个HTTP状态码
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "输入参数冲突")
public class ConflictException extends RuntimeException{

  private static final long serialVersionUID = -3738414943541540052L;

  public ConflictException() {
    super();
  }

  public ConflictException(String message) {
    super(message);
  }
  
}
