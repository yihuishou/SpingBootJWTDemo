package com.controller;

import com.exception.CustomException;
import com.exception.CustomUnauthorizedException;
import com.vo.ResponseBean;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常控制处理器
 *
 * @author Wang926454
 * @date 2018/8/30 14:02
 */
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {

        return new ResponseBean(401, "无权访问(Unauthorized):" + e.getMessage(), null, null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean handle401(UnauthorizedException e) {

        return new ResponseBean(401, "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")", null, null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseBean handle401(UnauthenticatedException e) {

        return new ResponseBean(401, "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)", null, null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseBean handle401(CustomUnauthorizedException e) {

        return new ResponseBean(401, "无权访问(Unauthorized):" + e.getMessage(), null, null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseBean methoErro(HttpRequestMethodNotSupportedException e) {

        return new ResponseBean(401, "访问方式无效(Method invalid):" + e.getMessage(), null, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResponseBean handle(HttpServletRequest request, CustomException e) {

        return new ResponseBean(410, e.getMessage(), null, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseBean globalException(HttpServletRequest request, Throwable t) {

        t.printStackTrace();
        return new ResponseBean(this.getStatus(request).value(), t.getMessage(), null, null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
