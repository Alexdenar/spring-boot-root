package com.hotdog.springboot.config.jwt;

import com.hotdog.springboot.config.jwt.HTTPHotdogAuthorizeAttribute;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hotdog on 2017/3/30.
 * 注册JWTfilter
 */
@Configuration
public class JWTFilterRegistration {

    @Bean
    public FilterRegistrationBean jwtFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HTTPHotdogAuthorizeAttribute httpFilter = new HTTPHotdogAuthorizeAttribute();
        registrationBean.setFilter(httpFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/user/findUsers");
        urlPatterns.remove("/user/oauth/token");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
