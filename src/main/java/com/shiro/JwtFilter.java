package com.shiro;

import com.alibaba.fastjson.JSON;
import com.exception.CustomException;
import com.exception.ShiroJwtDecodeException;
import com.exception.ShiroJwtSignatureVerificationException;
import com.exception.ShiroJwtTokenExpiredException;
import com.vo.ResponseBean;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by LadyLady on 2018-09-12.
 */

public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 过期时间5分钟
    @Value("${shiro.jwt.accessTokenExpireTime:3000}")
    private Long accessTokenExpireTime;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        // 判断用户是否想要登入
        if (this.isLoginAttempt(request, response)) {
            // 进行Shiro的登录UserRealm

            try {

                this.executeLogin(request, response);

            } catch (Exception e) {

                //token无效处理
                //开始检查token能不能刷新

                String message = e.getMessage();

                if (e instanceof ShiroJwtSignatureVerificationException) {

                    message = "Token签名验证失败 (" + e.getMessage() + ")";

                } else if (e instanceof ShiroJwtDecodeException) {

                    message = "Token格式错误 (" + e.getMessage() + ")";

                } else if (e instanceof ShiroJwtTokenExpiredException) {

                    if (refreshToken(request, response)) {
                        return true;
                    }

                    message = "Token刷新失败，Token已过期 (" + e.getMessage() + ")";

                }

                this.response401(request, response, message);

                return false;

                // this.forward401(request, response, message);
            }

        }
        return true;
    }

    // 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        this.sendChallenge(request, response);

        return false;
    }

    private boolean refreshToken(ServletRequest request, ServletResponse response) {

        String token = this.getToken(request, response);

        String tokenCreateTime = JwtUtil.getCreateTime(token);

        Long uuid = JwtUtil.getUuID(token);

        String username = JwtUtil.getUsername(token);

        if (stringRedisTemplate.hasKey(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid)) {

            String tokenCreateTimeInRedis = stringRedisTemplate.opsForValue().get(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid);

            if (tokenCreateTimeInRedis.equals(tokenCreateTime)) {

                // 通过说明该AccessToken时间戳与RefreshToken时间戳一致，进行AccessToken刷新

                // 获取当前时间戳
                Date newCreateTokenTime = new Date();
                // 获取RefreshToken剩余过期时间
                Long refreshTokenExpireTimeInRedis = stringRedisTemplate.getExpire(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid);

                // 设置RefreshToken中的时间戳为当前时间戳，且过期时间为之前剩余过期时间加上一个新的AccessToken过期时间
                stringRedisTemplate.opsForValue().set(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid, newCreateTokenTime.toString(), refreshTokenExpireTimeInRedis + accessTokenExpireTime, TimeUnit.SECONDS);

                // 刷新AccessToken，设置时间戳为当前时间戳
                token = JwtUtil.sign(uuid, username, newCreateTokenTime);

                // 将刷新的AccessToken存放在Response的Header中的Authorization字段返回
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization", "Bearer " + token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                // 进行Shiro的登录UserRealm
                JwtToken jwtToken = new JwtToken(token);
                // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
                this.getSubject(request, response).login(jwtToken);
                // 如果没有抛出异常则代表登入成功，返回true
                return true;

            }

        }
        return false;
    }

    /**
     * 检测Header里面是否包含Authorization字段，有就进行Token登录认证授权
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

        String authorization = getAuthzHeader(request);

        return authorization != null;
    }

    /**
     * 进行Token登录认证授权
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        JwtToken token = new JwtToken(this.getToken(request, response));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    private String getToken(ServletRequest request, ServletResponse response) {

        return getAuthzHeader(request).substring(7);
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    private void forward401(ServletRequest request, ServletResponse response, String message) {

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            // 传递错误信息msg
            request.setAttribute("message", message);
            httpServletRequest.getRequestDispatcher("/401").forward(httpServletRequest, httpServletResponse);
        } catch (ServletException e) {
            throw new CustomException("将非法请求转发到/401出现ServletException异常");
        } catch (IOException e) {
            throw new CustomException("将非法请求转发到/401出现IOException异常");
        }
    }

    private void response401(ServletRequest req, ServletResponse resp, String msg) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");

        try (PrintWriter out = httpServletResponse.getWriter()) {
            String data = JSON.toJSONString(new ResponseBean(401, "无权访问(Unauthorized):" + msg, null));
            data = data.replace("}", ",\"data\":null}");
            out.append(data);
        } catch (IOException e) {
            throw new CustomException("返回Response信息出现IOException异常");
        }
    }
}


