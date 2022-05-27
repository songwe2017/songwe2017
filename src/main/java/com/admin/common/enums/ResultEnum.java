package com.admin.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Songwe
 * @date 2022/5/23 21:00
 */
@ToString
public enum ResultEnum {
    
    SUCCESS(true, 0, "登录成功"),
    FAILED(false, 1, "登录失败");

    ResultEnum(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
    
    @Getter
    private boolean success;
    @Getter
    private int code;
    @Getter
    private String msg;
    
}
