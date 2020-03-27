package com.shizy.controller;


import com.shizy.utils.CacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redisTest")
@Api(tags = "redis", description = "RedisTestController")
public class RedisTestController {

    /**
     * java连接redis的方式
     * <p>
     * 1、Jedis
     * <p>
     * 2、RedisTemplate
     *
     *
     * redis数据查看
     *
     * 1、图像界面
     *
     * 2、命令行
     *
     */

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheUtil cacheUtil;

    @ApiOperation(value = "test1", notes = "")
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public Object test1() {
        Map result = new HashMap();


        result.put("111", "111");

        return result;
    }

}


















