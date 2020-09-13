package cn.jrycn.demo.sb2mybatis.service;

import com.github.pagehelper.PageInfo;
import cn.jrycn.demo.sb2mybatis.model.User;

public interface UserService {

  int addUser(User user);

  User findUser(String account);

  PageInfo<User> findAllUser(int pageNum, int pageSize);

  int modifyUser(User user);

  int removeUser(Integer id);

}
