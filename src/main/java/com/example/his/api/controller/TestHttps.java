package com.example.his.api.controller;

import com.example.his.api.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:
 * Package: com.example.his.api.controller
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/10 13:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/test-https")
public class TestHttps {
    @GetMapping("/demo")
    public R demo() {
        return R.ok("执行成功");
    }
}
