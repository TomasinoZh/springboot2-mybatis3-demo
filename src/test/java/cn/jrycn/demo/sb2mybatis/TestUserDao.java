package cn.jrycn.demo.sb2mybatis;

import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.jrycn.demo.sb2mybatis.dao.UserDao;
import cn.jrycn.demo.sb2mybatis.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按字母人顺序执行
public class TestUserDao {

  @Autowired
  private UserDao userDao;
  private static Integer id; // junit方法不能传输参数，只能通过内部成员变量传输

  @Test
  @Rollback(false)
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
    int count = userDao.insert(user);
    Assert.assertEquals(1, count);
    Assert.assertNotEquals("0", user.getId().toString());
  }

  @Test
  public void testB_Select() {
    User user = userDao.selectByAccount("abc");
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
    int count = userDao.update(user);
    Assert.assertEquals(1, count);
    Assert.assertEquals("abcd", user.getName());
  }

  @Test
  public void testD_SelectAll() {
    List<User> list = userDao.selectAll();
    Assert.assertNotEquals(0L, list.size());
  }

  @Test
  @Rollback(false)
  public void testE_Delete() {
    int count = userDao.deleteById(this.id);
    Assert.assertEquals(1, count);
  }

}
