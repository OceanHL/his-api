package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service.controller.form
 * Description: 登录表单上送参数
 *
 * @Author Ocean_jhl
 * @Create 2024/6/12 19:33
 * @Version 1.0
 */
@Data
public class LoginForm {
    @NotBlank(message = "username不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$", message = "username内容不正确")
    private String username;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "password内容不正确")
    private String password;
}
