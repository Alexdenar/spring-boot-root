package com.hotdog.springboot.model.mongo;

import org.springframework.data.annotation.Id;

/**
 * Created by hotdog on 2017/3/28.
 */
public class MongoDemo {

    //id属性是给mongodb用的，用@Id注解修饰
    @Id
    private String id;

    private String name;

    private int age;

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoInfo [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
