package com.example.his.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: CorsConfig
 * Package: com.example.his.api.config
 * Description: 允许跨域
 *
 * @Author Ocean_jhl
 * @Create 2024/6/9 22:48
 * @Version 1.0
 */
@Configuration // 全局配置允许跨域
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许的路径
                .allowedOriginPatterns("*") // 允许什么源访问
                .allowCredentials(true) // 允许发送Cookie信息【如果设为true，浏览器端必须在跨域请求中设置withCredentials: true】
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH") // 允许的HTTP方法
                .allowedHeaders("*") // 允许的请求头字段
                .maxAge(3600); // 预检请求的缓存时间（以秒为单位）。在3600秒（1小时）内，对于同一个跨域请求，不会再次发送预检请求。
    }
}
