package com.example.his.api.db.mapper;

import com.example.his.api.db.pojo.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* @author 87647
* @description 针对表【tb_user(用户表)】的数据库操作Mapper
* @createDate 2024-06-05 22:05:27
* @Entity db.pojo.UserEntity
*/
public interface UserMapper {
    public Set<String> searchUserPermissions(int userId);

    public Integer login(Map param);

    public String searchUsernameById(int userId);

    /**
     * 更新用户密码
     * @param param
     * @return 更新条数，若为1则修改成功
     */
    public int updatePassword(Map param);

    /**
     * 通过分页查询用户信息列表
     * @param param
     * @return
     */
    public ArrayList<HashMap> searchByPage(Map param);

    /**
     * 通过条件查询用户数量
     * @param param
     * @return
     */
    public long searchCount(Map param);

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    public int insert(UserEntity user);

    /**
     * 通过userId查询用户数据
     * @param userId
     * @return
     */
    public HashMap searchById(int userId);

    /**
     * 通过userId更新用户信息
     * @return
     */
    public int update(Map param);

    /**
     * 通过 userId 批量删除用户
     * @param ids
     * @return
     */
    public int deleteByIds(Integer[] ids);

    /**
     * 通过userId更改状态（1-在职、2-离职）
     * @param userId
     * @return
     */
    public int dismissById(int userId);
}