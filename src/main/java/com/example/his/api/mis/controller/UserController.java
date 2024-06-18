package com.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.his.api.common.PageUtils;
import com.example.his.api.common.R;
import com.example.his.api.mis.controller.form.LoginForm;
import com.example.his.api.mis.controller.form.SearchUserByPageForm;
import com.example.his.api.mis.controller.form.UpdatePasswordForm;
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
        return R.ok().put("result", true).put("token", token).put("permissions", permissions);
    }

    @GetMapping("/logout")
    @SaCheckLogin
    public R logout() {
        // 从令牌中解密出来userId
        final int userid = StpUtil.getLoginIdAsInt();
        // 销毁web端的令牌
        StpUtil.logout(userid, "Web");
        return R.ok();
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin // 只有登录后才能修改密码
    public R updatePassword(@RequestBody @Valid UpdatePasswordForm form) {
        // 从令牌中获取 userId
        final int userId = StpUtil.getLoginIdAsInt();
        final HashMap<String, Object> param = new HashMap<>() {{
            put("userId", userId);
            put("password", form.getPassword());
            put("newPassword", form.getNewPassword());
        }};

        final int rows = userService.updatePassword(param);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR) // 只有拥有【ROOT】或【USER:SELECT】权限的用户才能查询
    public R searchByPage(@RequestBody @Valid SearchUserByPageForm form) {
        log.info("form: {}", form);
        final Integer page = form.getPage();
        final Integer length = form.getLength();
        int start = (page - 1) * length; // 起始下标
        final Map param = BeanUtil.beanToMap(form);// 把Form对象转换成Map对象，因为Form对象中含有后端验证表达式，该对象仅用于web层，不适合传入到service层
        param.put("start", start);
        final PageUtils pageUtils = userService.searchByPage(param);
        return R.ok().put("page", pageUtils);
    }
}
