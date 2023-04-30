package com.population.flow.controller;

import com.population.flow.entity.Result;
import com.population.flow.entity.Tenancy;
import com.population.flow.service.TenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenancy")
public class TenancyController {
    @Autowired
    TenancyService service;

    @PostMapping("/register")
    public Result register(Tenancy tenancy){
        if (service.save(tenancy)){
            return new Result(200,"登记成功",null);
        }else {
            return new Result(500,"登记失败",null);
        }
    }

    @PostMapping("/getTenancy")
    public Result getVehicle(String key){
        return new Result(200,"",service.getTenancy(key));
    }

    @GetMapping("/tenancyList")
    public Result getList(){
        return new Result(200,"",service.list());
    }
}
