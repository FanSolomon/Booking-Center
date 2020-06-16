package com.fansolomon.feignConsumer.service;

import com.fansolomon.feignConsumer.config.FullLogConfiguration;
import com.fansolomon.feignConsumer.model.User;
import com.fansolomon.feignConsumer.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "booking-service", fallback = HelloServiceFallback.class, configuration = FullLogConfiguration.class)
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping("/hello1")
    String hello(@RequestParam("name") String name);

    @RequestMapping("/hello2")
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping("/hello3")
    String hello(@RequestBody User user);
}
