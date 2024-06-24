package com.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.his.api.common.PageUtils;
import com.example.his.api.common.R;
import com.example.his.api.db.pojo.UserEntity;
import com.example.his.api.mis.controller.form.*;
import com.example.his.api.mis.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/12 19:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/mis/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginForm form) {
        log.info("login入参: {}", form);
        // 把Form对象转换成Map对象，因为Form对象中含有后端验证表达式，该对象仅用于web层，不适合传入到service层
        final Map<String, Object> param = BeanUtil.beanToMap(form);

        // 获取登录用户的【主键值】
        System.out.println(DruidStatManagerFacade.getInstance().getDataSourceStatDataList());;
        final Integer userId = userService.login(param);
        // 如果登录失败，给前端返回的 result 为 false
        if (userId == null) {
            return R.ok().put("result", false);
        }

        /*
         *  实现同端互斥效果，把该用户Web端的令牌销毁。
         *  在其他浏览器上已经登录的该用户，令牌就失效了，实现同端互斥
         */
        StpUtil.logout(userId, "Web");

        // 通过会话对象，向SaToken传递userId，指定Web端使用
        StpUtil.login(userId, "Web");
        // 生成新的令牌字符串，标记该令牌是给Web端用户使用的
        final String token = StpUtil.getTokenValueByLoginId(userId, "Web");
        // 获取用户的权限列表
        final List<String> permissions = StpUtil.getPermissionList();
        // 向前端返回数据
//        Map <String, Object> result = new HashMap<>();
//        result.put("result", true);
//        result.put("token", token);
//        result.put("permissions", permissions);
//        return R.ok().put("data", result);
        final R result = R.ok().put("result", true).put("token", token).put("permissions", permissions);
        log.info("login响应: {}", result);
        return result;
    }

    @GetMapping("/logout")
    @SaCheckLogin
    public R logout() {
        log.info("------logout------无入参");
        // 从令牌中解密出来userId
        final int userid = StpUtil.getLoginIdAsInt();
        // 销毁web端的令牌
        StpUtil.logout(userid, "Web");
        final R result = R.ok();
        log.info("logout响应: {}", result);
        return result;
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin // 只有登录后才能修改密码
    public R updatePassword(@RequestBody @Valid UpdatePasswordForm form) {
        log.info("updatePassword入参: {}", form);
        // 从令牌中获取 userId
        final int userId = StpUtil.getLoginIdAsInt();
        final HashMap<String, Object> param = new HashMap<>() {{
            put("userId", userId);
            put("password", form.getPassword());
            put("newPassword", form.getNewPassword());
        }};

        final int rows = userService.updatePassword(param);
        final R result = R.ok().put("rows", rows);
        log.info("updatePassword响应: {}", result);
        return result;
    }

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR) // 只有拥有【ROOT】或【USER:SELECT】权限的用户才能查询
    public R searchByPage(@RequestBody @Valid SearchUserByPageForm form) {
        log.info("searchByPage入参: {}", form);
        final Integer page = form.getPage();
        final Integer length = form.getLength();
        int start = (page - 1) * length; // 起始下标
        final Map param = BeanUtil.beanToMap(form);// 把Form对象转换成Map对象，因为Form对象中含有后端验证表达式，该对象仅用于web层，不适合传入到service层
        param.put("start", start);
        final PageUtils pageUtils = userService.searchByPage(param);
        final R result = R.ok().put("page", pageUtils);
        log.info("searchByPage响应: {}", result);
        return result;
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    public R insert(@RequestBody @Valid InsertUserForm form) {
        log.info("insert入参: {}", form);
        // InsertUserForm 实例转化为 UserEntity 实例
        final UserEntity user = BeanUtil.toBean(form, UserEntity.class);
        user.setStatus(1); // 1-在职、2-离职
        // Integer[] 转为 JSON 格式
        // JSONUtil.parseArray(form.getRole()) 生成 JSONArray 类型
        // 生成字符串方式1：JSONUtil.parseArray(form.getRole()).toJSONString(0) --》 0表示空格数量
        // 生成字符串方式2：JSONUtil.parseArray(form.getRole()).toString()
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        final int rows = userService.insert(user);
        final R result = R.ok().put("rows", rows);
        log.info("insert响应: {}", result);
        return result;
    }

    /**
     * 通过userId查询用户数据
     * @param form
     * @return
     */
    @PostMapping("/searchById")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public R searchById(@RequestBody @Valid SearchUserByIdForm form) {
        log.info("searchById入参: {}", form);
        final HashMap map = userService.searchById(form.getUserId());
        final R result = R.ok().put("result", map);
        log.info("searchById响应: {}", result);
        return result;
    }

    /**
     * 通过userId更新用户信息
     * @return
     */
    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    public R update(@RequestBody @Valid UpdateUserForm form) {
        log.info("update入参: {}", form);
        // Bean 转 Map
        final Map param = BeanUtil.beanToMap(form);
        // role 属性转为 JSON格式
        param.replace("role", JSONUtil.parseArray(form.getRole()).toString());
        final int rows = userService.update(param);
        // 更新成功，所有端退出登录
        if (rows == 1) {
            // 退出该用户的【Web端、APP端、小程序端】，通过 userId 进行操作
            StpUtil.logout(form.getUserId());
        }
        R result = R.ok().put("rows", rows);
        log.info("update响应: {}", result);
        return result;
    }

    @PostMapping("/deleteByIds")
    @SaCheckPermission(value = {"ROOT", "USER:DELETE"}, mode = SaMode.OR)
    public R deleteByIds(@RequestBody @Valid DeleteUserByIdsForm form) {
        log.info("deleteByIds入参: {}", form);
        final int userId = StpUtil.getLoginIdAsInt();
        // 不能删除自己的账户
        if (ArrayUtil.contains(form.getIds(), userId)) {
            return R.error("您不能删除自己的账号");
        }
        final int rows = userService.deleteByIds(form.getIds());
        // 通过 userId 将删除的账号进行退出操作
        if (rows > 0) {
            for (Integer id : form.getIds()) {
                StpUtil.logout(id);
            }
        }
        R result = R.ok().put("rows", rows);
        log.info("deleteByIds响应: {}", result);
        return result;
    }

    @PostMapping("/dismissById")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    public R dismissById(@RequestBody @Valid DismissForm form) {
        log.info("dismissById入参: {}", form);
        final int rows = userService.dismissById(form.getUserId());
        // 更新状态成功【1-在职、2-离职】，则将该用户下线
        if (rows > 0) {
            // 通过 userId 将用户下辖
            StpUtil.logout(form.getUserId().intValue());
        }
        final R result = R.ok().put("rows", rows);
        return result;
    }
}
