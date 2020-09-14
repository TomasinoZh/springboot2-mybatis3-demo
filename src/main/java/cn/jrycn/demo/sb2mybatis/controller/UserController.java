package cn.jrycn.demo.sb2mybatis.controller;

import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import cn.jrycn.demo.sb2mybatis.dto.JsonResult;
import cn.jrycn.demo.sb2mybatis.exception.NotFoundException;
import cn.jrycn.demo.sb2mybatis.model.User;
import cn.jrycn.demo.sb2mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "用户管理")
@RequestMapping(value = "/user")
public class UserController {

  private final static Logger logger = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ApiOperation("创建用户")
  public JsonResult<Integer> addUser(@Valid @RequestBody User user) {
    Date now = new Date();
    user.setCreateTime(now);
    user.setLastLoginTime(now);
    user.setLastLoginIp("0.0.0.0");
    user.setLoginCount(0);
    user.setIsEnable(true);
    userService.addUser(user);
    return new JsonResult<>(user.getId());
  }

  @RequestMapping(value = "/modify", method = RequestMethod.PUT)
  @ApiOperation("修改用户")
  public JsonResult<Integer> modifyUser(@Valid @RequestBody User user) {
    userService.modifyUser(user);
    return new JsonResult<>(user.getId());
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ApiOperation("分页查询用户")
  public JsonResult<PageInfo<User>> findAllUser(
      @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
    return new JsonResult<>(userService.findAllUser(pageNum, pageSize));
  }

  @RequestMapping(value = "/one", method = RequestMethod.GET)
  @ApiOperation("查询单个用户")
  public JsonResult<User> findUser(
      @Valid @RequestParam(name = "account", required = true) String account) {
    User data = userService.findUser(account);
    if (data == null) {
      throw new NotFoundException();
    } else {
      return new JsonResult<>(data);
    }
  }

  @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
  @ApiOperation("删除用户")
  public JsonResult<Integer> removeUser(
      @Valid @PathVariable(name = "id", required = true) Integer id) {
    userService.removeUser(id);
    return new JsonResult<>(id);
  }
}
