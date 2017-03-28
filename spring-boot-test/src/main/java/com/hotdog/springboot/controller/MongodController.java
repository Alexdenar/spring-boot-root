package com.hotdog.springboot.controller;

import com.hotdog.springboot.model.mongo.MongoDemo;
import com.hotdog.springboot.repositories.MongoDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hotdog on 2017/3/28.
 */
@RestController
@RequestMapping("/mongo")
public class MongodController {

    @Autowired
    private MongoDemoRepository mongoDemoRepository;

    @RequestMapping("/save")
    public String save(){
        /*MongoDemo demoInfo = new MongoDemo();
        demoInfo.setName("张三");
        demoInfo.setAge(20);
        mongoDemoRepository.save(demoInfo);

        demoInfo = new MongoDemo();
        demoInfo.setName("李四");
        demoInfo.setAge(30);
        mongoDemoRepository.save(demoInfo);*/

        MongoDemo demoInfo = new MongoDemo();
        demoInfo.setName("赵乾");
        demoInfo.setAge(24);
        demoInfo.setNickname("hotdog");
        mongoDemoRepository.save(demoInfo);

        return "ok";
    }

    @RequestMapping("/find")
    public List<MongoDemo> find(){
        return mongoDemoRepository.findAll();
    }

    @RequestMapping("/findByName")
    public MongoDemo findByName(){
        return mongoDemoRepository.findByName("张三");
    }

    @RequestMapping("/findByNickname")
    public MongoDemo findByNickname(){
        return mongoDemoRepository.findByNickname("hotdog");
    }
}
