package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_customer
 */
@Data
public class CustomerEntity implements Serializable {
    private Integer id;

    private String name;

    private String sex;

    private String tel;

    private String photo;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}