package com.hotdog.springboot.impl;

import com.hotdog.springboot.inf.IMessageService;
import com.hotdog.springboot.mapper.MessageMapper;
import com.hotdog.springboot.model.mybatis.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hotdog on 2017/3/24.
 */
@Service("IMessageService")
@Transactional(rollbackFor={Exception.class})
public class MessageServiceImpl implements IMessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public int insert(Message message) {
        return 0;
    }

    @Override
    public int update(Message message) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Message queryById(Integer integer) {
        return null;
    }

    @Override
    public List<Message> findMessageInfo() {
        return messageMapper.findMessageInfo();
    }

    @Override
    public int count() {
        return messageMapper.count();
    }

    @Override
    public List<Message> pageQuery() {
        return null;
    }
}
