package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/24 21:21
 * @Version 1.0
 */
@Data
public class DeleteUserByIdsForm {
    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;
}
