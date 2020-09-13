package cn.jrycn.demo.sb2mybatis.interceptor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelper {

  private final static Logger logger = LoggerFactory.getLogger(HttpHelper.class);

  public static String getBodyString(HttpServletRequest request) {
    StringBuffer sb = new StringBuffer();
    InputStream inputStream;
    BufferedReader bufferedReader;
    try {
      // 将数据保存到数组中，每次读取的时候，都读取一遍
      inputStream = request.getInputStream();
      // 将字节数组当做输出的目的地
      // 字节流转换为字符流（处理流）
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        sb.append(line);
      }
    } catch (Exception e) {
      logger.error("数据读取异常", e);
    }
    return sb.toString();
  }

}
