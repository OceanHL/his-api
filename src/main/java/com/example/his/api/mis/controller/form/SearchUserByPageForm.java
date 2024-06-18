package com.example.his.api.mis.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller.form
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/18 20:56
 * @Version 1.0
 */
@Data
public class SearchUserByPageForm {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 10, max = 50, message = "每页条数必须在10到50之间")
    private Integer length;

    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{0,10}$", message = "姓名只能为简体中文")
    private String name;

    @Pattern(regexp = "^男$|女$", message = "性别只能为男或女")
    private String sex;

    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}$", message = "角色只能为英文和数字和简体中文")
    private String role;

    @Min(value = 1, message = "部门ID不能小于1")
    private Integer deptId;

    @Min(value = 1, message = "状态不能小于1")
    @Max(value = 2, message = "状态不能大于2")
    private Integer status;
}
