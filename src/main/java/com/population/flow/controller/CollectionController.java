package com.population.flow.controller;

import com.population.flow.entity.Collection;
import com.population.flow.entity.Result;
import com.population.flow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService service;

    @PostMapping("/register")
    public Result register(Collection collection){
        if (service.save(collection)){
            return new Result().setCode(200).setMessage("信息采集成功");
        }else {
            return new Result().setCode(500).setMessage("服务器错误");
        }
    }

    @GetMapping("/chart_province")
    public Result chart_province(){
        return new Result(200,"",service.PieChart_Data());
    }

    @GetMapping("/chart_month")
    public Result chart_month(){
        return new Result(200,"",service.LineChart_Data());
    }

    @GetMapping("/getData")
    public Result getData(){
        return new Result(200,"",service.list());
    }

}
