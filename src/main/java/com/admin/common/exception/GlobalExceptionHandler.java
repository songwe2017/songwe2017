package com.admin.common.exception;

import com.admin.common.base.R;
import com.admin.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * @author Songwe
 * @since 2022/6/6 22:52
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public R globalException(GlobalException e) {
        log.error(e.getMsg(), e);
        return R.failed().code(e.getStatus()).msg(e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R duplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return R.failed().msg("数据库已存在该记录!");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.failed().msg("全局异常处理");
    }

    ///**
    // * 自定义异常
    // * */
    //@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    //@ExceptionHandler(value = CustomException.class)
    //public R processExcepetion(CustomException e){
    //    log.error("位置:{} -> 错误信息:{}", e.getMethod() ,e.getLocalizedMessage());
    //    return R.failed(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    //}
    //
    ///**
    // * 拦截表单参数校验
    // */
    //@ResponseStatus(HttpStatus.OK)
    //@ExceptionHandler({BindException.class})
    //public static R bindException(BindException e){
    //    BindingResult bindingResult = e.getBindingResult();
    //    return R.failed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    //}
    //
    ///**
    // * 拦截JSON参数校验
    // */
    //@ResponseStatus(HttpStatus.OK)
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //public static R bindException(MethodArgumentNotValidException e){
    //    BindingResult bindingResult = e.getBindingResult();
    //    return R.failed(Objects.requireNonNull(bindingResult.getFieldError().getDefaultMessage()));
    //}
    //
    ///**
    // * 路径参数缺失异常
    // */
    //@ExceptionHandler(MissingPathVariableException.class)
    //public R missingPathVariableException(MissingPathVariableException e){
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultEnum.PATH_VARIABLE_DEFECTS_ERROR);
    //}
    //
    ///**
    // * 参数类型不匹配错误
    // */
    //@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    //public R methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultEnum.ARGUMENT_TYPE_MISMATCH);
    //}
    //
    ///**
    // * 参数格式错误
    // */
    //@ExceptionHandler(HttpMessageNotReadableException.class)
    //public R httpMessageNotReadable(HttpMessageNotReadableException e){
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultEnum.FORMAT_ERROR);
    //}
    //
    ///**
    // * 请求方式不支持
    // */
    //@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    //public R httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e){
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultEnum.REQ_METHOD_NOT_SUPPORT);
    //}
    //
    ///**
    // * 捕捉所有Shiro异常(401)
    // */
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    //@ExceptionHandler(ShiroException.class)
    //public R handle401(ShiroException e) {
    //    e.printStackTrace();
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultCode.UNLAWFUL,"无权访问(Unauthorized):"+e.getMessage());
    //}
    //
    ///**
    // * 单独捕捉Shiro(UnauthorizedException)异常 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常(401)
    // */
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    //@ExceptionHandler(UnauthorizedException.class)
    //public R handle401(UnauthorizedException e) {
    //    e.printStackTrace();
    //    return R.failed(ResultCode.UNLAWFUL, "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")");
    //}
    //
    ///**
    // * 单独捕捉Shiro(UnauthenticatedException)异常（401）
    // * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
    // */
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    //@ExceptionHandler(UnauthenticatedException.class)
    //public R handle401(UnauthenticatedException e) {
    //    e.printStackTrace();
    //    return R.failed(ResultCode.UNLAWFUL, "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)");
    //}
    //
    ///**
    // * 捕捉404异常 (404)
    // */
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(NoHandlerFoundException.class)
    //public R handle(NoHandlerFoundException e) {
    //    e.printStackTrace();
    //    return R.failed(ResultCode.NOT_FOUND, e.getMessage());
    //}
    //
    ///**
    // * 捕捉用户登录失败拦截
    // * */
    //@ExceptionHandler(FailureException.class)
    //public R failureException(FailureException e) {
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(e.getMessage());
    //}
    //
    ///**
    // * 捕捉重复提交失败拦截
    // * */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(RepeatSubmitException.class)
    //public R repeatSubmitException(RepeatSubmitException e) {
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(e.getMessage());
    //}
    //
    ///**
    // * 捕捉用户注册登录失败拦截
    // * */
    //@ExceptionHandler(TokenException.class)
    //public R tokenException(TokenException e) {
    //    e.printStackTrace();
    //    log.error("错误信息{}", e.getLocalizedMessage());
    //    return R.failed(ResultCode.TOKEN_ERROR,e.getMessage());
    //}
    //
    ///**
    // * 通用异常
    // */
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //@ExceptionHandler(Exception.class)
    //public R exception(Exception e){
    //    e.printStackTrace();
    //    handleOther();
    //    return R.failed(ResultEnum.UNKNOWN_EXCEPTION);
    //}
}
