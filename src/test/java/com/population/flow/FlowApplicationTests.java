package com.population.flow;

import com.baidu.aip.face.AipFace;
import com.population.flow.mapper.CollectionMapper;
import com.population.flow.mapper.UserMapper;
import com.population.flow.service.CollectionService;
import com.population.flow.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
class FlowApplicationTests {
    @Autowired
    CollectionMapper collectionMapper;
    @Test
    void contextLoads() throws IOException, JSONException {
        AipFace client = new AipFace("27988888", "cpxKMAfIuzgu3yTv3jElOMVg", "z4EzGKdfaghd9qUroWdx6IQwZ9l97hTf");

        // 构造模拟人像图片。 取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串
//        String path = "D:\\face.png";
//        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = "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";

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 调用Api进行人脸检测
        /**
         * image 图片的url或者base64字符串b
         * imageType 图片形式（URL，BASE64）
         * options Map中配置基本参数,null:使用默认配置
         */
        JSONObject res = client.detect(encode, "BASE64", options);
        System.out.println(res.toString(2));
    }
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Test
    void test() {
    }

    @Autowired
    CollectionService collectionService;
    @Test
    void  chart_data(){
        System.out.println(collectionService.PieChart_Data());
    }

}