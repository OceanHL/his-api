package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_action
 */
@Data
public class ActionEntity implements Serializable {
    private Integer id;

    private String actionCode;

    private String actionName;

    private static final long serialVersionUID = 1L;
}