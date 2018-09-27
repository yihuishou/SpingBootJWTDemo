package com.controller;

import com.mapper.AccountMapper;
import com.model.Account;
import com.shiro.JwtUtil;
import com.shiro.ShiroEnum;
import com.vo.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by LadyLady on 2018-09-19.
 */
@RestController
public class UserController {

    @Value("${shiro.jwt.refreshTokenExpireTime:6000}")
    private Long refreshTokenExpireTime;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("login")
    public ResponseBean login(@RequestBody Account account, HttpServletResponse httpServletResponse) {

        Account user = new Account();

        user.setUsername(account.getUsername());

        user = accountMapper.selectOne(user);

        if (user == null) {
            return new ResponseBean<Account>(200, "用户不存在", null, null);
        }


        if (user.getPassword().equals(account.getPassword())) {

            Date createTokenTime = new Date();

            String token = JwtUtil.sign(user.getUuid(), user.getUsername(), createTokenTime);

            stringRedisTemplate.opsForValue().set(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + user.getUuid(), createTokenTime.toString(), refreshTokenExpireTime, TimeUnit.SECONDS);

            httpServletResponse.addHeader("Authorization", "Bearer " + token);

            return new ResponseBean<Account>(200, "登陆成功", token, null);
        } else {
            return new ResponseBean<Account>(200, "用户名或密码错误", null, null);
        }

    }

    @RequiresRoles("{admin}")
    @GetMapping("teacher")
    public ResponseBean teacher() {

        return new ResponseBean<Account>(200, "teacher", null, null);

    }

    @GetMapping("test2")
    public ResponseBean test2() {

        Subject subject = SecurityUtils.getSubject();
        // 登录了返回true
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "您已经登录了(You are already logged in)", null, null);
        } else {
            return new ResponseBean(200, "你是游客(You are guest)", null, null);
        }

    }

    @RequiresAuthentication
    @GetMapping("test")
    public ResponseBean test() {

        return new ResponseBean(200, "success", null, null);

    }

}
