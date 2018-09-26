package com.controller;

import com.exception.CustomUnauthorizedException;
import com.vo.ResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LadyLady on 2018-09-12.
 */

@RestController
public class UnauthorizedController {

    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized(HttpServletRequest request) {

        String message = request.getAttribute("message").toString();

        if (message == null) {
            throw new CustomUnauthorizedException("访问被拒绝");
        }

        throw new CustomUnauthorizedException(message);
    }

}
