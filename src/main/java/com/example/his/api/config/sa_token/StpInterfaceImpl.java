package com.example.his.api.config.sa_token;

import cn.dev33.satoken.stp.StpInterface;
import com.example.his.api.db.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

;

/**
 * ClassName:
 * Package: com.example.his.api.config.sa_token
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/6 22:34
 * @Version 1.0
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserMapper userMapper;

    /**
     * 返回一个用户所拥有的权限集合
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        ArrayList<String> list = new ArrayList<>();
        int userId = Integer.parseInt(loginId.toString());
        // 简洁方法 return new ArrayList<>(set);
        Set<String> set = userMapper.searchUserPermissions(userId);
        list.addAll(set);
        return list;
    }

    /**
     * 【本项目不通过角色鉴权】返回一个用户所拥有的角色标识集合
     * @deprecated
     * @param loginId
     * @param loginKey
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
}
