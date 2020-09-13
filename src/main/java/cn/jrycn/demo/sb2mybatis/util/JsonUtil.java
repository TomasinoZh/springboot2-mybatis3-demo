package cn.jrycn.demo.sb2mybatis.util;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

  /**
   * 优雅输出json
   * 
   * @param json
   * @return
   * @throws IOException
   */
  public static String pretty(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Object obj = mapper.readValue(json, Object.class);
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
  }

  public static void main(String[] args) throws IOException {
    // 已知一个json 字符串
    String json = "{\"name\":\"sojson\",\"age\":4,\"domain\":\"https://www.sojson.com\"}";
    // 求优雅输出
    System.out.println(JsonUtil.pretty(json));
  }

}
