package com.population.flow.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.population.flow.entity.Result;
import com.population.flow.entity.User;
import com.population.flow.service.UserService;
import com.population.flow.utils.Base64Util;
import com.population.flow.utils.ImageVerificationCode;
import com.population.flow.utils.RandomAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Result result;

    @PostMapping("/login")
    public Result Login (String username,String password){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        userQueryWrapper.eq("password",Base64Util.encryptBASE64(password.getBytes()));
        User one = userService.getOne(userQueryWrapper);
        if (one!=null){
            return new Result().setCode(200).setMessage("登录成功").setData(one);
        }else {
            return result.setCode(500).setMessage("账号或密码错误");
        }
    }

    @RequestMapping("/register")
    public Result Register(String phone,String password){
        User user = new User();
        String username = RandomAccountUtil.randomDigitNumber(7);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        if (userService.getOne(wrapper)==null){
            user.setUsername(username);
            user.setPhone(phone);
            user.setPassword(Base64Util.encryptBASE64(password.getBytes()));
            if (userService.save(user)){
                return result.setCode(200).setMessage("注册成功").setData(user);
            }else {
                return result.setCode(500).setMessage("注册失败");
            }
        }else {
            return result.setCode(500).setMessage("账号已存在");
        }
    }

    @PostMapping("/image")
    public Result GetVerifiCode() throws IOException {
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        Map<String, Object> texts = new HashMap<>();
        texts.put("text", ivc.getText());
        ivc.output(image);//将验证码图片响应给客户端 指定地址
        String msg = texts != null ? "成功" : "数据查询失败请重试";
        return result.setCode(200).setMessage(msg);
    }

    @PostMapping("/update")
    public Result Update(User user){
        if (userService.Update(user)){
            UpdateWrapper<User> userWrapper=new UpdateWrapper<>();
            userWrapper.eq("username",user.getUsername());
            return result.setCode(200).setMessage("信息更新成功").setData(userService.getOne(userWrapper));
        }else {
            return result.setCode(500).setMessage("操作失败，请重新在操作一遍");
        }
    }

    @PostMapping("/getUser")
    public Result GetUser(User user){
        UpdateWrapper<User> userWrapper=new UpdateWrapper<>();
        userWrapper.eq("username",user.getUsername());
        User one = userService.getOne(userWrapper);
        if (one!=null){
            return new Result(200,"",one);
        }else {
            return new Result(500,"！错误",null);
        }
    }

    @PostMapping("/getUsers")
    public Result UserList(int current,int size){
        Page<User> page = userService.GetUserList(current, size);
        if (page.getTotal()>0){
            return new Result(200,null,page);
        }else {
            return new Result(500,"服务器错误",null);
        }
    }

    @PostMapping("/deleteUser")
    public Result DeleteUser(String username){
        if (userService.remove(new QueryWrapper<User>().eq("username",username))){
            return new Result(200,"删除成功",null);
        }else {
            return new Result(500,"服务器错误",null);
        }
    }

    @PostMapping("/search")
    public Result Search(String key){
        Page<User> page = userService.Search(key);
        if (page.getTotal()>0){
            return new Result(200,"查找成功",page);
        }else {
            return new Result(500,"找不到相关用户信息",null);
        }
    }

    @GetMapping("/chart_age")
    public Result chart_age(){
        return new Result(200,"",userService.RingChart_Data());
    }

    @GetMapping("/chart_sex")
    public Result chart_sex(){
        return new Result(200,"",userService.RingChart_Data2());
    }
}
