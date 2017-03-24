package com.hotdog.springboot.mapper;

import com.hotdog.springboot.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hotdog on 2017/3/24.
 */
@Repository
public interface MessageMapper {
    public List<Message> findMessageInfo();

    public int count();
}
