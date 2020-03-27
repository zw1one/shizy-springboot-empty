package com.shizy.controller;


import com.shizy.utils.CacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisTest")
@Api(tags = "redis", description = "RedisTestController")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheUtil cacheUtil;

    @ApiOperation(value = "test1", notes = "")
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public Object test1() {


        /**
         * java连接redis的方式
         *
         * 1、Jedis
         * 2、RedisTemplate
         *
         * redis数据查看
         *
         * 1、图形界面
         * 2、命令行
         *
         *
         */

        System.out.println();

        //key
        redisTemplate.opsForValue().set("shizy:redisTest:userName", "shizy");//重新set会覆盖
        redisTemplate.opsForValue().set("shizy:redisTest:userNameGG", "shizyGG", 60, TimeUnit.SECONDS);

        String userName = cacheUtil.getObjectFromCache("shizy:redisTest:userName");

        //hash
        redisTemplate.opsForHash().put("shizy:redisTest:userAge", "shizy", "111");
        redisTemplate.opsForHash().put("shizy:redisTest:userAge", "lizihua", "222");
        redisTemplate.expire("shizy:redisTest:userAge", 10, TimeUnit.MINUTES);

        String userAge = (String) redisTemplate.opsForHash().get("shizy:redisTest:userAge", "shizy");

        //list
        List list = new ArrayList();
        list.add("shizy");
        list.add("lizihua");
        list.add("chuhaobo");
        list.add("huoboyi");
        cacheUtil.setListToCache("shizy:redisTest:names", list);

        List names = cacheUtil.getListFromCache("shizy:redisTest:names");

        return null;
    }

}


















