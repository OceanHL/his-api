package com.example.his.api.mis.controller.form.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form.vo
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/7/18 20:43
 * @Version 1.0
 */
@Data
public class CheckupVo {
    @NotBlank(message = "体检项目不能为空")
    @Length(max = 50, message = "体检项目不能超过50个字符")
    private String title;

    @NotBlank(message = "体检内容不能为空")
    @Length(max = 500, message = "体检内容不能超过500个字符")
    private String content;
}
