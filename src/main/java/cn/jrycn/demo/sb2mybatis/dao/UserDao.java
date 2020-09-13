package cn.jrycn.demo.sb2mybatis.dao;


import java.util.List;
import cn.jrycn.demo.sb2mybatis.model.User;

public interface UserDao {

  int insert(User data);

  int update(User data);

  User selectByAccount(String account);

  List<User> selectAll();

  int deleteById(Integer id);

}
