package com.fansolomon.feignConsumer.controller;

import com.fansolomon.feignConsumer.model.User;
import com.fansolomon.feignConsumer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FeignConsumerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/feign-consumer")
    public String helloConsumer() {
        long start = System.currentTimeMillis();
        String result = helloService.hello();
        long end = System.currentTimeMillis();
        log.info("spend time : {}", (end-start));
        return result;
    }

    @RequestMapping("/feign-consumer2")
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("fansolomon")).append("\n");
        sb.append(helloService.hello("fansolomon",18)).append("\n");
        sb.append(helloService.hello(new User("fansolomon", 18))).append("\n");
        long end = System.currentTimeMillis();
        log.info("spend time : {}", (end-start));
        return sb.toString();
    }
}
