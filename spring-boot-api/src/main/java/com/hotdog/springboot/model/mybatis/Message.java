package com.hotdog.springboot.model.mybatis;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by hotdog on 2017/3/24.
 */
public class Message {
    private int id;
    private String ip;
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss.SSS")
    private Date insertDate;
    private String nickName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
