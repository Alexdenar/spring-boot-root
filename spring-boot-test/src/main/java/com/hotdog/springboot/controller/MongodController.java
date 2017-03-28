package com.hotdog.springboot.controller;

import com.hotdog.springboot.model.mongo.MongoDemo;
import com.hotdog.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hotdog on 2017/3/28.
 */
@RestController
public class MongodController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/mongo/test")
    public void mongotest () {
        userRepository.deleteAll();

        // 创建三个User，并验证demo总数
        userRepository.save(new MongoDemo(1L, "hotdog", 30));
        userRepository.save(new MongoDemo(2L, "mama", 40));
        userRepository.save(new MongoDemo(3L, "kaka", 50));
        int size = userRepository.findAll().size();
        Assert.assertEquals(3, size);

        // 删除一个demo，再验证demo总数
        /*MongoDemo u = userRepository.findOne(1L);
        userRepository.delete(u);
        int size1 = userRepository.findAll().size();
        Assert.assertEquals(2, size1);*/

        // 删除一个demo，再验证demo总数
        /*u = userRepository.findByNickname("hotdog");
        userRepository.delete(u);
        int size2 = userRepository.findAll().size();
        Assert.assertEquals(1, size2);*/
    }
}
