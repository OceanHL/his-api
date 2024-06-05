package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName tb_goods
 */
@Data
public class GoodsEntity implements Serializable {
    private Integer id;

    private String code;

    private String title;

    private String description;

    private Object checkup1;

    private Object checkup2;

    private Object checkup3;

    private Object checkup4;

    private Object checkup;

    private String image;

    private BigDecimal initialPrice;

    private BigDecimal currentPrice;

    private Integer salesVolume;

    private Object type;

    private Object tag;

    private Integer partId;

    private Integer ruleId;

    private Integer status;

    private String md5;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}