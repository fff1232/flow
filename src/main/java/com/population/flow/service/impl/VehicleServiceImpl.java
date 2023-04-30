package com.population.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.Vehicle;
import com.population.flow.mapper.VehicleMapper;
import com.population.flow.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {
    @Autowired
    VehicleMapper mapper;
    @Override
    public List<Vehicle> getVehicle(String key) {
        QueryWrapper<Vehicle> wrapper=new QueryWrapper<>();
        wrapper.like("car_number",key).or().like("color",key).
                or().like("brand",key).
                or().like("model",key);
        return mapper.selectList(wrapper);
    }
}
