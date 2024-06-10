package com.example.his.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * ClassName:
 * Package: com.example.his.api.controller
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/9 22:22
 * @Version 1.0
 */
@RestController // 所有web方法返回结果都是JSON格式
@RequestMapping("/test-xss")
public class TestXSS {
    @PostMapping("/demo")
    public HashMap demo(String str ) {
        System.out.println(str);
        return new HashMap() {{
            put("msg", "HelloWorld");
        }};
    }
}
