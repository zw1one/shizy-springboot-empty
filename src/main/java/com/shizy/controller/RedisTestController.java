package com.shizy.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redisTest")
@Api(tags = "redis", description = "RedisTestController")
public class RedisTestController {

    @ApiOperation(value = "test1", notes = "")
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public Object test1() {
        Map result = new HashMap();


        result.put("111", "111");

        return result;
    }

}


















