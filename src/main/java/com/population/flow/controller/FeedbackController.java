package com.population.flow.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.population.flow.entity.Feedback;
import com.population.flow.entity.Result;
import com.population.flow.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService service;

    @PostMapping("/submit")
    public Result Submit(Feedback feedback){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        feedback.setDate(java.sql.Date.valueOf(formatter.format(date)));
        if (service.save(feedback)) {
            return new Result(200,"提交成功",null);
        }else {
            return new Result(500,"服务器错误",null);
        }
    }

    @GetMapping("/list")
    public Result getList(){
        return new Result(200,"success",service.list());
    }

    @PostMapping("/revise_state")
    public Result revise_State(int id,int state){
        UpdateWrapper<Feedback> wrapper=new UpdateWrapper<>();
        wrapper.eq("id",id);
        wrapper.set("state",state);
        if (service.update(wrapper)){
            return new Result(200,"修改成功",null);
        }else {
            return new Result(500,"服务器错误",null);
        }
    }
}
