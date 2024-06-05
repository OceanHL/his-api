package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_user
 */
@Data
public class UserEntity implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String openId;

    private String photo;

    private String name;

    private Object sex;

    private String tel;

    private String email;

    private Date hiredate;

    private Object role;

    private Integer root;

    private Integer deptId;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}