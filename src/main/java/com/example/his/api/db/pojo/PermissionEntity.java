package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_permission
 */
@Data
public class PermissionEntity implements Serializable {
    private Integer id;

    private String permissionName;

    private Integer moduleId;

    private Integer actionId;

    private static final long serialVersionUID = 1L;
}