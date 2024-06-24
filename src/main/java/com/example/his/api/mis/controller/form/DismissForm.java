package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/24 23:08
 * @Version 1.0
 */
@Data
public class DismissForm {
    /**
     * @NotNull 用于【非数组、非字符串】类型
     * @NotEemput 用于【数组】类型
     * @NotBlank 用于【字符串】类型
     */
    @NotNull(message = "userId不能为空")
    @Min(value = 1, message = "userId不能小于1")
    private Integer userId;
}
