package com.hotdog.springboot.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by hotdog on 2017/3/29.
 * 通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * 通过createRestApi函数创建Docket的Bean之后，
     * apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，
     * 并产生文档内容（除了被@ApiIgnore指定的请求）。
     * @return
     */

    /**
     * 在完成了上述配置后，其实已经可以生产文档内容，
     * 但是这样的文档主要针对请求本身，
     * 而描述主要来源于函数等命名产生，对用户并不友好，
     * 我们通常需要自己增加一些说明来丰富文档内容。
     * 我们通过@ApiOperation注解来给API增加说明、
     *      通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hotdog.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("请关注：https://github.com/Alexdenar/spring-boot-root")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("hotdog")
                .version("1.0")
                .build();
    }

}
