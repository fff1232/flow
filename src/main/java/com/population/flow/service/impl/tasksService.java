package com.population.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.population.flow.entity.Collection;
import com.population.flow.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
//TaskScheduler 任务调度程序
 
@Service
public class tasksService {
    @Autowired
    CollectionMapper mapper;
    // 秒 分 时 天 月 周几~
    // 0 * * * * 0-7  每个月的每天每时每分每秒周一到周七都会执行
 
    /**
     * 30 15 10 * * ? 每天10点15分30 执行
     *
     * 30 0/5 10,18 * * ? 每天10时18时每个五分钟执行
     * 0 15 10 ? * 1-6 每个月的周一到周六10.15分钟执行一次
     */
    @Scheduled(cron = "0 0 2 ? * *")
    public void check(){
        List<Collection> outDate = mapper.outdate();
        for (Collection collection : outDate) {
            mapper.delete(new QueryWrapper<Collection>().eq("id",collection.getId()));
        }
        System.out.println("已完成每日任务,今日清理"+outDate.size()+"条旧数据");
    }
}
