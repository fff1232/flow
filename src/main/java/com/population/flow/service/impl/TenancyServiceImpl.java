package com.population.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.Tenancy;
import com.population.flow.mapper.TenancyMapper;
import com.population.flow.service.TenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenancyServiceImpl extends ServiceImpl<TenancyMapper, Tenancy> implements TenancyService {
    @Autowired
    TenancyMapper mapper;

    @Override
    public List<Tenancy> getTenancy(String key) {
        QueryWrapper<Tenancy> wrapper=new QueryWrapper<>();
        wrapper.like("address",key).or().like("purpose",key).
                or().like("duration",key).
                or().like("date",key);
        return mapper.selectList(wrapper);
    }
}
