package com.population.flow.controller;

import com.population.flow.entity.New;
import com.population.flow.entity.Result;
import com.population.flow.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/new")
public class NewController {
    @Autowired
    NewService newService;

    @GetMapping("/list")
    public Result newList(){
        List<New> list = newService.list();
        list.sort(list.get(0));
        return new Result().setData(list).setCode(200);
    }

    @PostMapping("/update")
    public Result update(New n){
        if (newService.updateById(n)){
            return new Result().setCode(200).setMessage("修改成功");
        }else {
            return new Result().setCode(500).setMessage("修改失败");
        }
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        System.out.println(id);
        if (newService.removeById(id)){
            return new Result().setCode(200).setMessage("删除成功");
        }else {
            return new Result().setCode(500).setMessage("删除失败");
        }
    }

    @PostMapping("/add")
    public Result addNew(New n){
        if (newService.save(n)){
            return new Result().setCode(200).setMessage("增加成功");
        }else {
            return new Result().setCode(500).setMessage("增加失败");
        }
    }
}
