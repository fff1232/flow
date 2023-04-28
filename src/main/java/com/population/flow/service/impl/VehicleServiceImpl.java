package com.population.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.Vehicle;
import com.population.flow.mapper.VehicleMapper;
import com.population.flow.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {
}
