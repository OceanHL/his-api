package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/19 21:05
 * @Version 1.0
 */
@Data
public class InsertUserForm {
    /**
     * 用户名
     */
    @NotBlank(message = "username用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{5,20}$", message = "用户名格式错误")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "password密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "密码格式错误")
    private String password;

    /**
     * 姓名【简体中文】
     */
    @NotBlank(message = "name姓名不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,10}$", message = "姓名格式错误")
    private String name;

    /**
     * 性别
     */
    @NotBlank(message = "sex性别不能为空")
    @Pattern(regexp = "^(男|女)$", message = "性别格式错误")
    private String sex;

    /**
     * 电话
     */
    @NotBlank(message = "tel电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话格式错误")
    private String tel;

    /**
     * 邮箱
     */
    @NotBlank(message = "email邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 入职日期
     */
    @NotBlank(message = "hiredate入职日期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "入职日期格式错误")
    private String hiredate;

    /**
     * 角色列表
     */
    @NotEmpty(message = "role角色权限不能为空")
    private Integer[] role;

    /**
     * 部门id
     */
    @Min(value = 1, message = "deptId部门id不能小于1")
    private Integer deptId;
}
