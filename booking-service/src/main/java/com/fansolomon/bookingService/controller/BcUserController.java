package com.fansolomon.bookingService.controller;


import com.fansolomon.bookingService.entity.dto.ResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author zhangyuting
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/bcUser")
public class BcUserController {

    @RequestMapping("test")
    public ResultDTO test() {
        return new ResultDTO("ok");
    }
}
