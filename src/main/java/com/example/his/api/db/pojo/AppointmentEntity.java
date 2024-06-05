package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_appointment
 */
@Data
public class AppointmentEntity implements Serializable {
    private Integer id;

    private String uuid;

    private Integer orderId;

    private Date date;

    private String name;

    private String sex;

    private String pid;

    private Date birthday;

    private String tel;

    private String mailingAddress;

    private String company;

    private Integer status;

    private Date checkinTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}