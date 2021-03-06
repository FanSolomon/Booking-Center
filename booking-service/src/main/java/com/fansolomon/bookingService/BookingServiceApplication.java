package com.fansolomon.bookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://127.0.0.1:8080/swagger-ui.html来访问swagger页面
@EnableSwagger2
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.fansolomon")
public class BookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

}
