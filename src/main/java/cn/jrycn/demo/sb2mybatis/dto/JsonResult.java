package cn.jrycn.demo.sb2mybatis.dto;

public class JsonResult<T> {
  private T data;
  private int status;
  private String message;

  /**
   * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
   */
  public JsonResult() {
    this.status = 200;
    this.message = "操作成功！";
  }

  /**
   * 若没有数据返回，可以人为指定状态码和提示信息
   * 
   * @param code
   * @param msg
   */
  public JsonResult(int code, String msg) {
    this.status = code;
    this.message = msg;
  }

  /**
   * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
   * 
   * @param data
   */
  public JsonResult(T data) {
    this.data = data;
    this.status = 200;
    this.message = "操作成功";
  }

  /**
   * 有数据返回，状态码为 0，人为指定提示信息
   * 
   * @param data
   * @param msg
   */
  public JsonResult(T data, String msg) {
    this.data = data;
    this.status = 200;
    this.message = msg;
  }

  /**
   * 有数据返回，人为指定状态码，人为指定提示信息
   * 
   * @param data
   * @param msg
   */
  public JsonResult(T data, int code, String msg) {
    this.data = data;
    this.status = code;
    this.message = msg;
  }

  // 省略 get 和 set 方法
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int errCode) {
    this.status = errCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String errMsg) {
    this.message = errMsg;
  }

}
