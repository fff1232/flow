package com.population.flow.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.population.flow.entity.Result;
import com.population.flow.entity.User;
import com.population.flow.service.UserService;
import com.population.flow.utils.BaiduAiUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/face")
public class FaceController {
    @Autowired
    private BaiduAiUtils baiduAiUtils;
    @Autowired
    UserService userService;
    @Autowired
    Result result;
    @PostMapping("/check")
    public Result check(String image){
        if (baiduAiUtils.faceCheck(image)){
            return result.setCode(200).setMessage("已检测到人脸，请继续操作");
        }else {
            return result.setCode(500).setMessage("未检测到人脸，请正视镜头再次尝试");
        }
    }

    @PostMapping("/register")
    public Result register(String username, String image){
        if (baiduAiUtils.faceRegister(username,image)){
            return result.setCode(200).setMessage("添加人脸成功");
        }else {
            return result.setCode(500).setMessage("添加失败，请重新尝试");
        }
    }

    @PostMapping("/search")
    public Result search(String userId, String image){
        if (Objects.equals(baiduAiUtils.faceSearch(image), userId)){
            return new Result().setCode(200).setMessage("对比成功");
        }else {
            return new Result().setCode(500).setMessage("对比失败，请重新尝试");
        }
    }

    @PostMapping("/login")
    public Result login(String image){
        String uid=baiduAiUtils.faceSearch(image);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username", uid);
        User one = userService.getOne(userQueryWrapper);
        if (!StringUtils.isBlank(uid)){
            return new Result(200,"登录成功",one);
        }else {
            return result.setCode(500).setMessage("未识别到人脸，请注册后完成人脸注册后再使用此功能");
        }
    }

    @PostMapping("/update")
    public Result update(String userId, String image){
        if (baiduAiUtils.faceUpdate(userId,image)){
            return result.setCode(200).setMessage("更新成功");
        }else {
            return result.setCode(500).setMessage("更新失败，请重新尝试");
        }
    }


}
