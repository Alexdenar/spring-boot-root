package com.hotdog.springboot;

import com.hotdog.springboot.model.customProp.Audience;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by hotdog on 2017/3/29.
 * 新的启动类
 */

/**
 * @SpringBootApplication 注解等价于以默认属性使用 @Configuration ， @EnableAutoConfiguration 和 @ComponentScan
 *
 * @ServletComponentScan有此注解后，项目中如果需要使用java原生的servlet和filter，可以在类中使用注解实现，主要是配置Druid监控时需要用到
 */

@SpringBootApplication
@ServletComponentScan
//启动注解事务管理
@EnableTransactionManagement
//启用定时任务
@EnableScheduling
//开启自定义properties
@EnableConfigurationProperties(Audience.class)
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
