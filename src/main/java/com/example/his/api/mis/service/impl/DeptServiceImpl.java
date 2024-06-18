package com.example.his.api.mis.service.impl;

import com.example.his.api.db.mapper.DeptMapper;
import com.example.his.api.mis.service.DeptService;
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
 * @Create 2024/6/18 21:47
 * @Version 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    /**
     * 查询所有部门信息
     * @return ArrayList<HashMap>
     */
    @Override
    public ArrayList<HashMap> searchAllDept() {
        final ArrayList<HashMap> list = deptMapper.searchAllDept();
        return list;
    }
}
