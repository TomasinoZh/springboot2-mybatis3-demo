package cn.jrycn.demo.sb2mybatis;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.jrycn.demo.sb2mybatis.controller.UserController;
import cn.jrycn.demo.sb2mybatis.dto.JsonResult;
import cn.jrycn.demo.sb2mybatis.model.User;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@AutoConfigureMybatis // add
@ComponentScan(basePackages = {"cn.jrycn.demo.*"})
public class TestUserController {

  @Autowired
  private MockMvc mvc;
  private RequestBuilder request = null;

  // @Test
  public void testA_AddOne() throws Exception {
    request = MockMvcRequestBuilders.post("/user/add").contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"abc\",\"account\":\"abc\",\"password\":\"abc\",\"officeId\":1}");
    mvc.perform(request).andExpect(status().isOk()) // 添加断言
        // .andExpect(content().string("[]")) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
  }

  // @Test
  public int testB_GetOne() throws Exception {
    request = MockMvcRequestBuilders.get("/user/one").param("account", "abc");
    MvcResult rs = mvc.perform(request).andExpect(status().isOk()) // 添加断言
        // .andExpect(content().string("[]")) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
    String content = rs.getResponse().getContentAsString();

    ObjectMapper mapper = new ObjectMapper();
    JsonResult<User> myObjects =
        mapper.readValue(content, new TypeReference<JsonResult<User>>() {});

    User user = myObjects.getData();
    System.out.println("id: " + user.getId());
    return user.getId();
  }

  // @Test
  public void testC_ModifyOne(int id) throws Exception {
    request = MockMvcRequestBuilders.put("/user/modify").contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"abcd\",\"id\":" + id + "}");
    mvc.perform(request).andExpect(status().isOk()) // 添加断言
        // .andExpect(jsonPath("$.name").value("abcd")) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
  }

  // @Test
  public void testD_GetAll() throws Exception {
    MvcResult rs = mvc
        .perform(
            MockMvcRequestBuilders.get("/user/all").param("pageNum", "1").param("pageSize", "20"))
        .andExpect(status().isOk()) // 添加断言
        // .andExpect(content().string("hello,fishpro!"))// 添加断言
        .andDo(print()) // 添加执行
        .andReturn();// 添加返回
    String content = rs.getResponse().getContentAsString();
    System.out.println(content);
  }

  // @Test
  public void testE_RemoveOne(int id) throws Exception {
    System.out.println("id: " + id);
    request = MockMvcRequestBuilders.delete("/user/remove/" + id);
    mvc.perform(request).andExpect(status().isOk()) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
  }

  @Test
  public void testSuit() throws Exception {
    testA_AddOne();
    int id = testB_GetOne();
    testC_ModifyOne(id);
    testD_GetAll();
    testE_RemoveOne(id);
  }
}
