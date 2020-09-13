package cn.jrycn.demo.sb2mybatis;

import java.util.Date;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.pagehelper.PageInfo;
import cn.jrycn.demo.sb2mybatis.model.User;
import cn.jrycn.demo.sb2mybatis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按字母人顺序执行
public class TestUserService {
  @Autowired
  UserService userService;
  private static Integer id; // junit方法不能传输参数，只能通过内部成员变量传输

  @Test
  public void testA_Insert() {
    User user = new User();
    user.setAccount("abc");
    user.setName("abc");
    user.setOfficeId(1);
    user.setPassword("password");
    user.setCreateTime(new Date());
    user.setLastLoginIp("127.0.0.1");
    user.setLastLoginTime(new Date());
    user.setLoginCount(1);
    user.setIsEnable(false);
    int count = userService.addUser(user);
    Assert.assertEquals(1, count);
    Assert.assertNotEquals("0", user.getId().toString());
  }

  @Test
  public void testB_Select() {
    User user = userService.findUser("abc");
    System.out.println(user.getId());
    Assert.assertEquals("abc", user.getAccount());
    this.id = user.getId();
  }

  @Test
  @Rollback(false)
  public void testC_Update() {
    User user = new User();
    user.setAccount("abc");
    user.setName("abcd");
    user.setOfficeId(1);
    user.setPassword("password");
    user.setCreateTime(new Date());
    user.setLastLoginIp("127.0.0.1");
    user.setLastLoginTime(new Date());
    user.setLoginCount(1);
    user.setIsEnable(false);
    user.setId(this.id);
    int count = userService.modifyUser(user);
    Assert.assertEquals(1, count);
    Assert.assertEquals("abcd", user.getName());
  }

  @Test
  public void testD_SelectAll() {
    PageInfo<User> list = userService.findAllUser(1, 20);
    Assert.assertNotEquals(0L, list.getTotal());
  }

  @Test
  public void testE_Delete() {
    int count = userService.removeUser(this.id);
    Assert.assertEquals(1, count);
  }
}
