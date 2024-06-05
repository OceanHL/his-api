package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_customer_im
 */
@Data
public class CustomerImEntity implements Serializable {
    private Integer id;

    private Integer customerId;

    private Date loginTime;

    private static final long serialVersionUID = 1L;
}