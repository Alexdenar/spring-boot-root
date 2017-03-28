package com.hotdog.springboot.repositories;

import com.hotdog.springboot.model.mongo.MongoDemo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhaoqian
 * @create 2017-03-28 23:51
 * mail to zhaoqian@hisign.com.cn
 **/
public interface MongoDemoRepository extends MongoRepository<MongoDemo, String> {

    MongoDemo findByName(String name);

    MongoDemo findByNickname (String nickname);
}
