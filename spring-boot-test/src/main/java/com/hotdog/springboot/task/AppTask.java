package com.hotdog.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hotdog on 2017/3/24.
 */
@Component
public class AppTask {

    @Scheduled(cron = "0 06 18 * * ? ")
    public void task () {
        System.out.println("开启定时任务：" + new Date());
    }
}
