package com.example.his.api.db.mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author 87647
* @description 针对表【tb_role(角色表)】的数据库操作Mapper
* @createDate 2024-06-05 22:05:27
* @Entity db.pojo.RoleEntity
*/
public interface RoleMapper {
    /**
     * 查询所有角色信息
     * @return
     */
    public ArrayList<HashMap> searchAllRole();
}




