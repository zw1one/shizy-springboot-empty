package com.shizy.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mongoTest")
@Api(tags = "mongo", description = "MongoTestController")
public class MongoTestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "test1", notes = "")
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public Object test1() {

        System.out.println();

        Map record = new HashMap();
        record.put("name", "shizy");
        record.put("age", "11");

        mongoTemplate.save(record, "shizyInfo");

        List<HashMap> result = mongoTemplate.findAll(HashMap.class, "shizyInfo");

        System.out.println();

        return null;
    }

}


















