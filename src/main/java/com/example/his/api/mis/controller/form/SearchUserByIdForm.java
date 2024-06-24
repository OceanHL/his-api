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
 * @Create 2024/6/20 21:49
 * @Version 1.0
 */
@Data
public class SearchUserByIdForm {
    @NotNull(message = "userId不能为空")
    @Min(value = 1, message = "userId不能小于1")
    private Integer userId;
}
