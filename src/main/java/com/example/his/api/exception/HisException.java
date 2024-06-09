package com.example.his.api.exception;

import lombok.Data;

/**
 * ClassName:
 * Package: com.example.his.api.exception
 * Description: 自定义业务异常类
 *
 * @Author Ocean_jhl
 * @Create 2024/6/9 23:34
 * @Version 1.0
 */
@Data
public class HisException extends RuntimeException {
    private String msg;
    private int code = 500;

    public HisException(Exception e) {
        super(e);
        this.msg = "执行异常";
    }

    public HisException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public HisException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public HisException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public HisException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
