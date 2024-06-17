package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/16 21:59
 * @Version 1.0
 */
@Data
public class UpdatePasswordForm {
    /**
     * 旧密码
     */
    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "password必须包含字母和数字，且长度为6-20")
    String password;

    /**
     * 新密码
     */
    @NotBlank(message = "newPassword不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "newPassword必须包含字母和数字，且长度为6-20")
    private String newPassword;
}
