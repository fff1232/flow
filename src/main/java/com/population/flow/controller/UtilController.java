package com.population.flow.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.population.flow.entity.Result;
import com.population.flow.utils.OCRUtil;
import com.population.flow.utils.SendCode;
import com.population.flow.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
@RestController
@RequestMapping("/util")
public class UtilController {
    @Autowired(required = false)
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/getweather")
    public Result getWeather(String ip){
        return new Result().setData(WeatherUtil.getWerther(ip));
    }

    @PostMapping("/getCode")
    public Result getCode(String phone,String code,HttpServletRequest request) throws Exception {
        String captcha = (String) request.getSession().getAttribute("captcha");
        System.out.println(code+"-----"+captcha);
        System.out.println(request.getSession());
        if (code.equalsIgnoreCase(captcha)){
            SendCode sendCode = new SendCode();
            String phoneCode = sendCode.getCode(phone);
            request.getSession().setAttribute("phoneCode",phoneCode);
//            UserVo userVo = new UserVo();
//            userVo.setLabel(code2);
            return new Result(200,"发送成功",null);
        }else {
            return new Result(500,"验证码错误，请重新输入",null);
        }
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request , HttpServletResponse response){
        //定义response输出类型为image/jpeg
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //---------------------------生成验证码----------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码:  " + text);
        //将验证码放到session中
        request.getSession().setAttribute("captcha",text);
        System.out.println(request.getSession());
        //根据文本内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片,格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
