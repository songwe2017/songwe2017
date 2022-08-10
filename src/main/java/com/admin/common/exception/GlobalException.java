package com.admin.common.exception;

import com.admin.common.base.RCode;
import lombok.Getter;

/**
 * @author Songwe
 * @since 2022/6/6 22:51
 */
public class GlobalException extends RuntimeException{
    @Getter
    private int status = 500;
    @Getter
    private String msg;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(RCode rCode) {
        super(rCode.getMsg());
        this.status = rCode.getCode();
        this.msg = rCode.getMsg();
    }

    public GlobalException(RCode rCode, Throwable e) {
        super(rCode.getMsg(), e);
        this.status = rCode.getCode();
        this.msg = rCode.getMsg();
    }
}
