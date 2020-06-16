package com.fansolomon.bookingService.web;

import com.fansolomon.bookingService.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String index() throws InterruptedException {
        List<ServiceInstance> instance = client.getInstances("booking-service");

        //让处理线程随机等待几秒
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime:{}", sleepTime);
        Thread.sleep(sleepTime);

        log.info("/hello,host:{},service_id:{}", instance.get(0).getHost(), instance.get(0).getServiceId());
        return "Hello Spring Cloud :D !";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "Hello" + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user) {
        return "Hello" + user.getName() + "," + user.getAge();
    }
}
