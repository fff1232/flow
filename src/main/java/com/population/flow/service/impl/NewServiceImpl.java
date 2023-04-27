package com.population.flow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.New;
import com.population.flow.mapper.NewMapper;
import com.population.flow.service.NewService;
import org.springframework.stereotype.Service;

@Service
public class NewServiceImpl extends ServiceImpl<NewMapper, New> implements NewService {
}
