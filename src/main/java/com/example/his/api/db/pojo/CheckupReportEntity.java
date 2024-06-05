package com.example.his.api.db.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_checkup_report
 */
@Data
public class CheckupReportEntity implements Serializable {
    private Integer id;

    private Integer appointmentId;

    private String resultId;

    private Integer status;

    private String filePath;

    private String waybillCode;

    private Date waybillDate;

    private Date date;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}