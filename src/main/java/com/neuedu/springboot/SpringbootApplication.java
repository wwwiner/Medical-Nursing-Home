package com.neuedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages="com.neuedu.springboot")
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);


		
		/*
		 * componentScan:组件扫描   
		 * EnableTransactionManagement:工程支持事务处理
		 * Controller:修饰控制层的类  支持组件扫描
		 * RestController : 修饰控制层的类  支持组件扫描  同时支持json转换
		 * RequestMapping: 请求映射
		 * GetMapping: 请求映射  只接收get形式的请求
		 * PostMapping:请求映射  只接收post形式的请求
		 * ResponseBody:json转换
		 * PathVariable: 解析restful风格路径中的参数
		 * Autowired: 自动的依赖注入
		 * Service:修饰Service层的类  支持组件扫描
		 * Transactional : 所修饰的service层类中的方法进行事务处理
		 * Mapper :修饰Mapper层的接口  支持组件扫描
		 * Select  update  insert  delete  results   options ..  mybatis的注解开发
		 * 
		 */
			
		
	}

}
