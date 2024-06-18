package com.example.his.api.mis.service.impl;

import com.example.his.api.db.mapper.RoleMapper;
import com.example.his.api.mis.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service.impl
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/18 21:36
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        final ArrayList<HashMap> list = roleMapper.searchAllRole();
        return list;
    }
}
