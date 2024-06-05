package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_role
 */
@Data
public class RoleEntity implements Serializable {
    private Integer id;

    private String roleName;

    private Object permissions;

    private String desc;

    private Object defaultPermissions;

    private Integer systemic;

    private static final long serialVersionUID = 1L;
}