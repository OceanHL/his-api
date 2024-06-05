package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_module
 */
@Data
public class ModuleEntity implements Serializable {
    private Integer id;

    private String moduleCode;

    private String moduleName;

    private static final long serialVersionUID = 1L;
}