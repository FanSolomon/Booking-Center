package com.fansolomon.bookingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Colin
 * @since 2020-7-29
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    //http://127.0.0.1:8080/swagger-ui.html     原路径
    //http://127.0.0.1:8080/doc.html     原路径

    //进行swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  //指定api类型为swagger2
                    .apiInfo(apiInfo())     //用于定义api文档汇总信息
                    .select().apis(RequestHandlerSelectors
                        .basePackage("com.fansolomon.bookingService.controller")) //指定controller包
                    .paths(PathSelectors.any()) //所有controller
                    .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Booking-Center接口api")   //文档页标题
                .contact(new Contact("FanSolomon"
                        ,"https://github.com/FanSolomon"
                        ,"jetonlyy@gmail.com")) //联系人信息
                .description("Booking-Center接口api") //详细信息
                .version("1.0.0")   //文档版本号
                .termsOfServiceUrl("https://github.com/FanSolomon") //网站地址
                .build();
    }
}
