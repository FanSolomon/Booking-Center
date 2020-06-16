package com.fansolomon.feignConsumer.service.fallback;

import com.fansolomon.feignConsumer.model.User;
import com.fansolomon.feignConsumer.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务降级实现类
 *
 * @author zhangyuting
 * @since 2020-4-23 19:10:14
 */
@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "error";
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User("未知",0);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "error";
    }
}
