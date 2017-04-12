package com.hotdog.springboot.config.dubbox;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:dubbo.xml")
public class DubboxConfig {
}
