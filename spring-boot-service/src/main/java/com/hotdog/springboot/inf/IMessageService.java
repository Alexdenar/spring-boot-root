package com.hotdog.springboot.inf;

import com.hotdog.springboot.model.mybatis.Message;

import java.util.List;

/**
 * Created by hotdog on 2017/3/24.
 */
public interface IMessageService extends IBaseService<Message, Integer>{

    public static final String SERVICE_NAME = "IMessageService";

    public List<Message> findMessageInfo();

    public int count();

    public List<Message> pageQuery();
}
