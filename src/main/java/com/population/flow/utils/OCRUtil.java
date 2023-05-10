package com.population.flow.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.population.flow.entity.IdCard;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class OCRUtil {
    public static IdCard idCard=new IdCard();

    public static IdCard ocr(String img) {
        String host = "https://cardnumber.market.alicloudapi.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String appcode = "e0402cf950794d9d8e5bce1afbd86c15";
        String imgFile = "F:\\Desktop\\20.jpg";
        String method = "POST";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");

        Map<String, String> querys = new HashMap<>();
        // 对图像进行base64编码
//        String imgBase64 = img_base64(imgFile);

        //configure配置
        JSONObject configObj = new JSONObject();
        configObj.put("side", "face");

        String config_str = configObj.toString();

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", img);
        if (configObj.size() > 0) {
            requestObj.put("configure", config_str);
        }
        String bodys = requestObj.toString();

        JSONObject res_obj;
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            int stat = response.getStatusLine().getStatusCode();
            if (stat != 200) {
                System.out.println("Http code: " + stat);
                System.out.println("http header error msg: " + response.getFirstHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
                return null;
            }

            String res = EntityUtils.toString(response.getEntity());
            res_obj = JSON.parseObject(res);
            org.json.JSONObject jsonObj = new org.json.JSONObject(res);
            idCard.setAddress(jsonObj.get("address").toString());
            idCard.setName(jsonObj.get("name").toString());
            idCard.setSex(jsonObj.get("sex").toString());
            idCard.setNum(jsonObj.get("num").toString());
            idCard.setBirth(jsonObj.get("birth").toString());
            System.out.println(res_obj.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCard;
    }
}
