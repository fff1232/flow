package com.population.flow.utils;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    private String fileLocation="file:D:/image/";
    private String filePath="/image/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //注册配置类，使用addResourceHandlers方法，将本地路径fileLocation映射到filePath路由上。
        registry.addResourceHandler(filePath).addResourceLocations(fileLocation);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
