package com.population.flow.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.population.flow.entity.Result;
import com.population.flow.entity.Vehicle;
import com.population.flow.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    VehicleService service;

    @PostMapping("/register")
    public Result register(Vehicle vehicle){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        vehicle.setDate(java.sql.Date.valueOf(formatter.format(date)));
        if (service.save(vehicle)){
            return new Result(200,"登记成功",null);
        }else {
            return new Result(500,"登记失败请联系管理员",null);
        }
    }

    @GetMapping("/own_vehicles")
    public Result own_vehicles(String username){
        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<Vehicle>().eq("username", username);
        return new Result(200,"",service.getOne(queryWrapper));
    }

    @PostMapping("/getVehicle")
    public Result getVehicle(String key){
        return new Result(200,"",service.getVehicle(key));
    }


    @GetMapping("/vehicle_list")
    public Result vehicle_list(){
        return new Result(200,"",service.list());
    }
}
