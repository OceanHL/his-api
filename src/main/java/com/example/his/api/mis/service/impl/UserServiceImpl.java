package com.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.example.his.api.db.mapper.UserMapper;
import com.example.his.api.mis.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service.impl
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/11 23:26
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public Integer login(Map param) {
        String username = MapUtil.getStr(param, "username");
        String password = MapUtil.getStr(param, "password");
        MD5 md5 = MD5.create();
        // 将用户名生成哈希值
        String temp = md5.digestHex(username);
        // 从指定位置开始,截取指定长度的字符串，如果fromIndex为正数，则向后截取指定length长度，如果为负数，则向前截取length长度。
        String tempStart = StrUtil.subWithLength(temp, 0, 6);
        // 切割指定位置之后部分的字符串
        String tempEnd = StrUtil.subSuf(temp, temp.length() - 3);
        // 对用户原始密码生成哈希值，用哈希值前六位和后三位字符，与原始密码拼接，然后再用哈希算法生成加密结果
        password = md5.digestHex(tempStart + password + tempEnd).toUpperCase();
        param.replace("password", password);
        Integer userId = userMapper.login(param);
        return userId;
    }

    @Override
    public int updatePassword(Map param) {
        final Integer userId = MapUtil.getInt(param, "userId");
        final String username = userMapper.searchUsernameById(userId);

        final MD5 md5 = MD5.create();
        String password = MapUtil.getStr(param, "password");
        // 根据用户名对密码进行混淆
        final String temp = md5.digestHex(username);
        // 截取前6位
        final String tempStart = StrUtil.subWithLength(temp, 0, 6);
        // 截取后3位
        final String tempEnd = StrUtil.subSuf(temp, temp.length() - 3);
        // 生成加密后的密码
        password = md5.digestHex(tempStart + password + tempEnd).toUpperCase();
        param.replace("password", password);

        // 对新密码进行加密
        String newPassword = MapUtil.getStr(param, "newPassword");
        newPassword = md5.digestHex(tempStart + newPassword + tempEnd).toUpperCase();
        param.replace("newPassword", newPassword);

        final int rows = userMapper.updatePassword(param);
        return rows;
    }
}
