package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_appointment_restriction
 */
@Data
public class AppointmentRestrictionEntity implements Serializable {
    private Integer id;

    private Date date;

    private Integer num1;

    private Integer num2;

    private Integer num3;

    private String remark;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}