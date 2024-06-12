package com.example.his.api.mis.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.his.api.common.R;
import com.example.his.api.mis.controller.form.LoginForm;
import com.example.his.api.mis.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
