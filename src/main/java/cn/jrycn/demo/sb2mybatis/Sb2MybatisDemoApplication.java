package cn.jrycn.demo.sb2mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jrycn.demo.sb2mybatis.dao")
public class Sb2MybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb2MybatisDemoApplication.class, args);
	}
}
