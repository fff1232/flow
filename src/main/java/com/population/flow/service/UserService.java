package com.population.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.population.flow.entity.User;
import com.population.flow.entity.UserVo;

import java.util.List;


public interface UserService extends IService<User> {
    boolean Update(User user);

    Page<User> GetUserList(int current,int size);

    Page<User> Search(String key);

    List<UserVo> RingChart_Data();

    List<UserVo> RingChart_Data2();
}
