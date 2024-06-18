package com.example.his.api.db.mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author 87647
* @description 针对表【tb_dept(部门表)】的数据库操作Mapper
* @createDate 2024-06-05 22:05:27
* @Entity db.pojo.DeptEntity
*/
public interface DeptMapper {
    /**
     * 查询所有部门信息
     * @return
     */
    public ArrayList<HashMap> searchAllDept();
}




