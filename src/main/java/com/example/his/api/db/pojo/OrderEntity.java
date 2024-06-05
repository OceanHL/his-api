package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName tb_order
 */
@Data
public class OrderEntity implements Serializable {
    private Integer id;

    private Integer customerId;

    private Integer goodsId;

    private String snapshotId;

    private String goodsTitle;

    private BigDecimal goodsPrice;

    private Integer number;

    private BigDecimal amount;

    private String goodsImage;

    private String goodsDescription;

    private String outTradeNo;

    private String transactionId;

    private String outRefundNo;

    private Integer status;

    private Date createDate;

    private Date createTime;

    private Date refundDate;

    private Date refundTime;

    private static final long serialVersionUID = 1L;
}