package com.population.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.population.flow.entity.Vehicle;

import java.util.List;

public interface VehicleService extends IService<Vehicle> {
    List<Vehicle> getVehicle(String key);
}
