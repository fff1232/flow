package com.population.flow.controller;

import com.population.flow.entity.Result;
import com.population.flow.utils.WeatherUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {

    @PostMapping("/getweather")
    public Result getWeather(String ip){
        return new Result().setData(WeatherUtil.getWerther(ip));
    }
}
