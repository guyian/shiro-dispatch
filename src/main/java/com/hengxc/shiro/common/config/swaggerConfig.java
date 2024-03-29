package com.hengxc.shiro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chenguangxu
 * @create 2019/7/4 9:47
 */
@Configuration
@EnableSwagger2
public class swaggerConfig {

    //访问 uri
    //localhost:server.port/swagger-ui.html

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描路径下所有注解
                /*.apis(RequestHandlerSelectors.basePackage("com.hengxc.shiro."))
                .paths(PathSelectors.any())
                .build();*/
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title("OA RESTful API")
                .build();
    }


}
