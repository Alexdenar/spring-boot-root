package com.hotdog.springboot.model.mongo;

import org.springframework.data.annotation.Id;

/**
 * Created by hotdog on 2017/3/28.
 */
public class MongoDemo {

    //id属性是给mongodb用的，用@Id注解修饰
    @Id
    private Long id;

    private String nickname;
    private Integer age;

    public MongoDemo(Long id, String nickname, Integer age) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
