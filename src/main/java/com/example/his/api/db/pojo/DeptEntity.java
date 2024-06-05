package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_dept
 */
@Data
public class DeptEntity implements Serializable {
    private Integer id;

    private String deptName;

    private String tel;

    private String email;

    private String desc;

    private static final long serialVersionUID = 1L;
}