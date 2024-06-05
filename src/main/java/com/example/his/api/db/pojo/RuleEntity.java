package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_rule
 */
@Data
public class RuleEntity implements Serializable {
    private Integer id;

    private String name;

    private String rule;

    private String remark;

    private static final long serialVersionUID = 1L;
}