package com.population.flow.utils;

import com.baidu.aip.face.AipFace;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
@Slf4j
public class BaiduAiUtils {

    @Value("${baidu.face.appId}")
    private String APP_ID;
    @Value("${baidu.face.apiKey}")
    private String API_KEY;
    @Value("${baidu.face.secretKey}")
    private String SECRET_KEY;
    @Value("${baidu.face.imageType}")
    private String IMAGE_TYPE;
    @Value("${baidu.face.groupId}")
    private String groupId;

    private AipFace client;

    private HashMap<String, String> map = new HashMap<>();
    private HashMap<String, Object> options = new HashMap<>();

    private BaiduAiUtils() {
        //图片质量控制 NONE: 不进行控制 LOW:较低的质量要求 NORMAL: 一般的质量要求 HIGH: 较高的质量要求 默认 NONE
        map.put("quality_control", "NORMAL");
        // 活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率) 默认NONE
        map.put("liveness_control", "LOW");
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
    }

    @PostConstruct
    public void init() {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    /**
     * 用户照片存入人脸库中
     */
    public Boolean faceRegister(String userId, String image) {
        JSONObject res = client.addUser(image, IMAGE_TYPE, groupId, userId, map);
        log.info("addUser result ：{}", res);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    /**
     * 更新人脸库中的用户照片
     */
    public Boolean faceUpdate(String userId, String image) {
        JSONObject res = client.updateUser(image, IMAGE_TYPE, groupId, userId, map);
        log.info("updateUser result ：{}", res);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    /**
     * 判断上传的图片中是否具有面部信息
     */
    public Boolean faceCheck(String image) {
        JSONObject res = client.detect(image, IMAGE_TYPE, options);
        log.info("detect result ：{}", res);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject resultObject = res.getJSONObject("result");
            Integer faceNum = resultObject.getInt("face_num");
            return faceNum == 1 ? true : false;
        } else {
            return false;
        }
    }

    /**
     * 1.搜索人脸库中相似的人脸并返回数据
     *
     * 2.判断人脸匹配得分（score）大于80分则认为是同一个人
     */
    public String faceSearch(String image) {
        JSONObject res = client.search(image, IMAGE_TYPE, groupId, options);
        log.info("search result ：{}", res);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0) {
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if (score > 80) {
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }

}


