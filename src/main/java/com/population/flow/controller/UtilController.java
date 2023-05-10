package com.population.flow.controller;

import com.population.flow.entity.Result;
import com.population.flow.entity.UserVo;
import com.population.flow.utils.OCRUtil;
import com.population.flow.utils.SendCode;
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

    @PostMapping("/getCode")
    public Result getCode(String phone) throws Exception {
        SendCode sendCode = new SendCode();
        String code = sendCode.getCode(phone);
        UserVo userVo = new UserVo();
        userVo.setLabel(code);
        return new Result(200,"发送成功",userVo);
    }

    @PostMapping("/ocr")
    public Result OCR_identify(String idcard){
        if (OCRUtil.ocr(idcard)!=null) {
            return new Result(200, "识别成功", OCRUtil.ocr(idcard));
        }else{
            return new Result(500, "请上传清晰的证件照或自行填写信息",null);
        }
    }
}
