package com.example.his.api.mis.service;

import com.example.his.api.common.PageUtils;
import com.example.his.api.db.pojo.UserEntity;

import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service
 * Description: mis端-用户模块业务层
 *
 * @Author Ocean_jhl
 * @Create 2024/6/11 23:24
 * @Version 1.0
 */
public interface UserService {
    public Integer login(Map param);

    public int updatePassword(Map param);

    /**
     * 通过页码查询
     * @param param
     * @return
     */
    public PageUtils searchByPage(Map param);

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    public int insert(UserEntity user);
}
