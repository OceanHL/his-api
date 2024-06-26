package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName tb_customer_location
 */
@Data
public class CustomerLocationEntity implements Serializable {
    private Integer id;

    private Integer customerId;

    private String blueUuid;

    private Integer placeId;

    private String createTime;

    private static final long serialVersionUID = 1L;
}