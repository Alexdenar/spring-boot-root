package com.hotdog.springboot.mapper;

import com.hotdog.springboot.model.mybatis.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hotdog on 2017/3/30.
 */
@Repository
public interface UserRepositoy {

    UserInfo findUserInfoByName(String name);

    List<UserInfo> findUsers();
}
