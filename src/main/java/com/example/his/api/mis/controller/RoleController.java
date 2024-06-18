package com.example.his.api.mis.controller;

import com.example.his.api.common.R;
import com.example.his.api.mis.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/18 21:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/mis/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/searchAllRole")
    public R searchAllRole() {
        final ArrayList<HashMap> list = roleService.searchAllRole();
        return R.ok().put("list", list);
    }
}
