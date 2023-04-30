package com.population.flow.utils;


import com.population.flow.entity.WeatherBean;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherUtil {
    public static WeatherBean weatherBean=new WeatherBean();
    public static WeatherBean getWerther(String ip){
        String host = "https://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = "e0402cf950794d9d8e5bce1afbd86c15";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", ip);
        HttpResponse response;
        {
            try {
                response = HttpUtils.doGet(host, path, method, headers, querys);
//                System.out.println(response.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //获取response的body
        Object resBody;
        try {
            String data=EntityUtils.toString(response.getEntity());
            JSONObject jsonObj = new JSONObject(data);
            resBody = jsonObj.get("result");
            JSONObject jsonObject = new JSONObject(resBody.toString());
            weatherBean.setWind_power(jsonObject.get("windpower").toString());
            weatherBean.setWind_direction(jsonObject.get("winddirect").toString());
            weatherBean.setWeather(jsonObject.get("weather").toString());
            weatherBean.setTemperature(jsonObject.get("temp").toString());
            weatherBean.setArea(jsonObject.get("city").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weatherBean;
    }
}
