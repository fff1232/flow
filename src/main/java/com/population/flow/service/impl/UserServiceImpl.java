package com.population.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.population.flow.entity.User;
import com.population.flow.entity.UserVo;
import com.population.flow.mapper.UserMapper;
import com.population.flow.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean Update(User user) {
        UpdateWrapper<User> userWrapper=new UpdateWrapper<>();
        userWrapper.eq("username",user.getUsername());
        if (userMapper.update(user,userWrapper)==1){
            return true;
        }
        return false;
    }

    @Override
    public Page<User> GetUserList(int current, int size) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("status",0);
        return userMapper.selectPage(new Page<>(current,size),wrapper);
    }

    @Override
    public Page<User> Search(String key) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("status",0);
        wrapper.like("id",key).or().like("name",key).
                or().like("phone",key).
                or().like("sex",key).
                or().like("number",key).
                or().like("address",key);
        return userMapper.selectPage(new Page<>(1,7),wrapper);
    }

    @Override
    public List<UserVo> RingChart_Data() {
        return userMapper.RingChart_Data();
    }

    @Override
    public List<UserVo> RingChart_Data2() {
        return userMapper.RingChart_Data2();
    }
}
