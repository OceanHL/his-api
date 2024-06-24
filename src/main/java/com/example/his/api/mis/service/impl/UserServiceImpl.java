package com.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.example.his.api.common.PageUtils;
import com.example.his.api.db.mapper.UserMapper;
import com.example.his.api.db.pojo.UserEntity;
import com.example.his.api.mis.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 通过页码查询
     * @param param
     * @return
     */
    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = new ArrayList<>();
        final long count = userMapper.searchCount(param);
        if (count > 0) {
            list = userMapper.searchByPage(param);
        }
        final Integer page = MapUtil.getInt(param, "page"); // 当前页码下标
        final Integer length = MapUtil.getInt(param, "length"); // 每页多少数据
        final PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    /**
     * 插入用户信息
     * @param user
     * @return 插入行数
     */
    @Override
    @Transactional
    public int insert(UserEntity user) {
        // 对密码进行混淆加密
        final MD5 md5 = MD5.create();
        final String temp = md5.digestHex(user.getUsername());
        final String tempStart = StrUtil.subWithLength(temp, 0, 6);
        final String tempEnd = StrUtil.subSuf(temp, temp.length() - 3);
        final String password = md5.digestHex(tempStart + user.getPassword() + tempEnd).toUpperCase();
        user.setPassword(password);
        final int rows = userMapper.insert(user);
        return rows;
    }

    /**
     * 通过userId查询用户数据
     * @param userId
     * @return
     */
    @Override
    public HashMap searchById(int userId) {
        final HashMap userInfo = userMapper.searchById(userId);
        return userInfo;
    }

    /**
     * 通过userId更新用户信息
     * @return
     */
    @Override
    public int update(Map param) {
        final int rows = userMapper.update(param);
        return rows;
    }

    /**
     * userId 批量删除用户
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int deleteByIds(Integer[] ids) {
        final int rows = userMapper.deleteByIds(ids);
        return rows;
    }
}
