package com.population.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.population.flow.entity.Tenancy;

import java.util.List;

public interface TenancyService extends IService<Tenancy> {
    List<Tenancy> getTenancy(String key);
}
