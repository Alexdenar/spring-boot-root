package com.hotdog.springboot.repositories;

import com.hotdog.springboot.model.mongo.MongoDemo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hotdog on 2017/3/28.
 */
@Repository
public interface UserRepository extends MongoRepository<MongoDemo, Long> {

    public MongoDemo findByNickname(String nickname);
}
