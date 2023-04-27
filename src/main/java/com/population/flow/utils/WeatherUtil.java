package com.population.flow.utils;


import com.population.flow.entity.WeatherBean;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherUtil {
    public static WeatherBean weatherBean=new WeatherBean();
    public static WeatherBean getWerther(String ip){
        String host = "http://saweather.market.alicloudapi.com";
        String path = "/ip-to-weather";
        String method = "GET";
        String appcode = "e0402cf950794d9d8e5bce1afbd86c15";
        Map<String, String> headers = new HashMap<>();
//        最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("ip", ip);
        querys.put("need3HourForcast", "0");
        querys.put("needAlarm", "0");
        querys.put("needHourData", "0");
        querys.put("needIndex", "0");
        querys.put("needMoreDay", "0");
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
            resBody = jsonObj.get("showapi_res_body");
            JSONObject jsonObject = new JSONObject(resBody.toString());
            Object now = jsonObject.get("now");
            JSONObject jsonObject1 = new JSONObject(now.toString());
            Object aqiDetail = jsonObject1.get("aqiDetail");
            weatherBean.setWind_direction(jsonObject1.get("wind_direction").toString());
            weatherBean.setWeather(jsonObject1.get("weather").toString());
            weatherBean.setWind_power(jsonObject1.get("wind_power").toString());
            weatherBean.setTemperature(jsonObject1.get("temperature").toString());
            JSONObject jsonObject2 = new JSONObject(aqiDetail.toString());
            weatherBean.setArea(jsonObject2.get("area").toString());
            System.out.println(weatherBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weatherBean;
    }
}
