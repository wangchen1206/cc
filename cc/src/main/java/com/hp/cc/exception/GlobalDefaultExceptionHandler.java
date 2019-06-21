package com.hp.cc.exception;


import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.alibaba.fastjson.JSON;
import com.hp.cc.common.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常信息
 *
 **/
@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    /**
     * ORB 和 stub 之间的应用程序级异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseBody
    public Result applicationException(ApplicationException e) {
        log.error("Application Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
    }


    /**
     * 方法参数效验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        //log.error("Argument not valid:", e);
        log.error(JSON.toJSONString(e.getBindingResult().getAllErrors()));
        return Result.createByErrorCodeMessage(Result.ResponseCode.BAD_REQUEST.getCode(), e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")));
    }

    /**
     *
     * 参数非法异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {IllegalStateException.class})
    @ResponseBody
    public Result illegalStateException(IllegalStateException e) {
        log.error("Argument not valid(Illegal):", e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.BAD_REQUEST.getCode(),e.getMessage());

    }

    /**
     * 无访问权限
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        log.error("Access Denied Exception:{}", e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.UNAUTHORIZED.getCode(),e.getMessage());

    }

    /**
     * 接口不存在异常
     *
     * @param request 请求的request
     * @return 响应体
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseBody
    public Result noHandlerFoundExceptionHandler(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("Url not found Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.NOT_FOUND.getCode(), "接口 [" + request.getRequestURI() + "] 不存在");
    }

    /**
     * Precondition failed异常
     *
     * @return 响应体
     */
    @ExceptionHandler(value = {ServletException.class})
    @ResponseBody
    public Result servletExceptionHandler(ServletException e) {
        log.error("Servlet Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.PRECONDITION_FAILED.getCode(), e.getMessage());
    }

    /**
     * ValidationException
     *
     * @return 响应体
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Result constraintViolationExceptionHandler(ValidationException e) {
        log.error("Validation Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * ValidationException
     *
     * @return 响应体
     */
    @ExceptionHandler(DisabledException.class)
    @ResponseBody
    public Result disabledExceptionHandler(DisabledException e) {
        log.error("DisabledException Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.USER_DISABLE.getCode(), e.getMessage());
    }

    /**
     * ValidationException
     *
     * @return 响应体
     */
    @ExceptionHandler(LockedException.class)
    @ResponseBody
    public Result lockedExceptionHandler(LockedException e) {
        log.error("LockedException Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.FORBIDDEN.getCode(), e.getMessage());
    }

    /**
     * UsernameNotFoundException
     *
     * @return 响应体
     */
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseBody
    public Result lockedExceptionHandler(Exception e) {
        log.error("UsernameNotFoundException Exception: "+e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.UNAUTHORIZED.getCode(), e.getMessage());
    }

    /**
     * 实现对异常的拦截，当错误在Bean的注解时
     *
     * @return 响应体
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public Result bindExceptionHandler(BindException e) {
        log.error("Bind Exception: " + e);
        log.error(JSON.toJSONString(e.getBindingResult().getAllErrors()));
        return Result.createByErrorCodeMessage(Result.ResponseCode.BAD_REQUEST.getCode(), e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")));
    }

    /**
     * Precondition failed异常
     *
     * @param req 请求体
     * @param e   异常
     * @return 响应体
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result defaultExceptionHandler(HttpServletRequest req, Exception e, Object handler) {
        String message;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                    req.getRequestURI(),
                    handlerMethod.getBean().getClass().getName(),
                    handlerMethod.getMethod().getName(),
                    e.getMessage());
        } else {
            message = e.getMessage();
        }
        log.error(message, e);
        return Result.createByErrorCodeMessage(Result.ResponseCode.INTERNAL_SERVER_ERROR.getCode(), "接口 [" + req.getRequestURI() + "] 内部错误");
    }

}
