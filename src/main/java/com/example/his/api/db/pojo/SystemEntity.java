package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_system
 */
@Data
public class SystemEntity implements Serializable {
    private Integer id;

    private String item;

    private String value;

    private String remark;

    private static final long serialVersionUID = 1L;
}