package cn.jrycn.demo.sb2mybatis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.jrycn.demo.sb2mybatis.dao.UserDao;
import cn.jrycn.demo.sb2mybatis.model.User;
import cn.jrycn.demo.sb2mybatis.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;// 这里会报错，但是并不会影响

  @Override
  public int addUser(User user) {
    return userDao.insert(user);
  }

  @Override
  public User findUser(String account) {
    return userDao.selectByAccount(account);
  }

  @Override
  public PageInfo<User> findAllUser(int pageNum, int pageSize) {
    if (pageNum <= 0) {
      pageNum = 1;
    }
    if (pageSize <= 0) {
      pageSize = 20;
    }
    // 将参数传给这个方法就可以实现物理分页了，非常简单。
    PageHelper.startPage(pageNum, pageSize);
    List<User> datas = userDao.selectAll();
    PageInfo result = new PageInfo(datas);
    return result;
  }

  @Override
  public int modifyUser(User user) {
    return userDao.update(user);
  }

  @Override
  public int removeUser(Integer id) {
    return userDao.deleteById(id);
  }

}
