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
import com.google.gson.Gson;
import cn.jrycn.demo.sb2mybatis.controller.UserController;
import cn.jrycn.demo.sb2mybatis.model.User;


@RunWith(SpringRunner.class)
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootTest(classes = UserController.class, webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebMvcTest(UserController.class)
@WebMvcTest(value = UserController.class, secure = false)
@AutoConfigureMybatis // add
@ComponentScan(basePackages = {"cn.jrycn.demo.*"})
// @MapperScan(basePackages = {"cn.jrycn.demo.sb2mybatis.dao"})
public class TestUserController {

  @Autowired
  private MockMvc mvc;
  private RequestBuilder request = null;
  private static Integer id = null;

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
  public void testB_GetOne() throws Exception {
    request = MockMvcRequestBuilders.get("/user/one").param("account", "abc");
    MvcResult rs = mvc.perform(request).andExpect(status().isOk()) // 添加断言
        // .andExpect(content().string("[]")) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
    String content = rs.getResponse().getContentAsString();
    Gson gson = new Gson();
    User user = gson.fromJson(content, User.class);
    System.out.println("id: " + user.getId());
    this.id = user.getId();
  }

  // @Test
  public void testC_ModifyOne() throws Exception {
    System.out.println("id: " + this.id);
    request = MockMvcRequestBuilders.put("/user/modify").contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"abcd\",\"id\":" + this.id + "}");
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
  public void testE_RemoveOne() throws Exception {
    System.out.println("id: " + this.id);
    request = MockMvcRequestBuilders.delete("/user/remove/" + this.id.toString());
    mvc.perform(request).andExpect(status().isOk()) // 添加断言
        .andDo(print())// 添加执行
        .andReturn();// 添加返回
  }

  @Test
  public void testSuit() throws Exception {
    testA_AddOne();
    testB_GetOne();
    testC_ModifyOne();
    testD_GetAll();
    testE_RemoveOne();
  }
}
