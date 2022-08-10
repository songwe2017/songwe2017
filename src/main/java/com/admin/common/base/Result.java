package com.admin.common.base;

import com.admin.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Songwe
 * @date 2022/5/23 18:39
 */
@Data
public class Result<R> implements Serializable {
    // 是否成功
    private boolean success;
    // 状态码
    private int code; 
    // 状态描述
    private String msg;
    // 返回数据
    private R data;
    
    private Result() {this.success = true;}

    public static <R> Result<R> success() {
        return new Result<>();
    }
    
    public static <R> Result<R> success(R data) {
        Result<R> response = new Result<>();
        response.setData(data);
        return response;
    }

    public static <R> Result<R> failed(String msg) {
        Result<R> response = new Result<R>();
        response.setSuccess(false);
        response.setCode(500);
        response.setMsg(msg);
        return response;
    }
    
    public static <R> Result<R> failed(ResultEnum resultEnum) {
        Result<R> response = new Result<R>();
        response.setSuccess(resultEnum.isSuccess());
        response.setCode(resultEnum.getCode());
        response.setMsg(resultEnum.getMsg());
        return response;
    }

    public static <R> Result<R> failed(Throwable exception) {
        Result<R> response = new Result<R>();
        response.setSuccess(false);
        response.setMsg(exception.getClass().getName() + "," + exception.getMessage());
        return response;
    }
    
}
