package com.example.his.api.mis.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/18 21:45
 * @Version 1.0
 */
public interface DeptService {
    /**
     * 查询所有部门信息
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> searchAllDept();
}
