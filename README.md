## springboot 2.0 集成 mybatis

### 环境：

* 开发工具：Intellij IDEA 2020.1.3
* springboot: **2.1.6.RELEASE**
* jdk：1.8.0_40
* maven:3.3.9
* alibaba Druid 数据库连接池：1.1.9

### 额外功能：
* PageHelper 分页插件


## 访问
http://localhost:8080/demo/user/all

## 访问swagger
http://localhost:8080/demo/swagger-ui.html

## JUnit测试
* 使用JUnit测试DAO
* 使用JUnit测试Service
* 使用JUnit测试RestController

## Springboot 三种拦截Rest API的方法-过滤器、拦截器、切片
```
过滤器：可以拿到原始的HTTP请求和响应信息，拿不到处理请求的方法值信息 
拦截器：既可以拿到HTTP请求和响应信息，也可以拿到请求的方法信息，拿不到方法调用的参数值信息 
切片：可以拿到请求方法的传入参数值，拿不到原始的HTTP请求和响应的对象
```

## 构建方法
```
mvn clean
# -Dmaven.test.skip=true 是跳过测试代码
mvn package -Dmaven.test.skip=true
mvn docker:build
```