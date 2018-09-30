package com.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.AccountMapper;
import com.mapper.RoleMapper;
import com.model.Account;
import com.model.Role;
import com.services.UserServices;
import com.shiro.JwtUtil;
import com.shiro.ShiroAESUtil;
import com.shiro.ShiroEnum;
import com.vo.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by LadyLady on 2018-09-19.
 */
@RestController
public class UserController {

    @Value("${shiro.jwt.refreshtokenexpireTime:6000}")
    private Long refreshTokenExpireTime;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("login")
    public ResponseBean login(@RequestBody Account account, HttpServletResponse httpServletResponse) {

        Account user = new Account();

        user.setUsername(account.getUsername());

        user = accountMapper.selectOne(user);

        if (user == null) {
            return new ResponseBean(200, "用户不存在", null);
        }

        if (ShiroAESUtil.decrypt(user.getPassword()).equals((account.getPassword()))) {

            Date createTokenTime = new Date();

            String token = JwtUtil.sign(user.getUuid(), user.getUsername(), createTokenTime);

            stringRedisTemplate.opsForValue().set(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + user.getUuid(), createTokenTime.toString(), refreshTokenExpireTime, TimeUnit.SECONDS);

            httpServletResponse.addHeader("Authorization", "Bearer " + token);

            return new ResponseBean(200, "登陆成功", token);
        } else {
            return new ResponseBean(200, "用户名或密码错误", null);
        }

    }

    @GetMapping("showAll")
    public ResponseBean selectAllAccount() {

        List<Account> accountList = userServices.selectAll();

        return new ResponseBean(200, null,  accountList);
    }

    @PostMapping("addUser")
    public ResponseBean addAccount(@RequestBody Account account) {

        Account user = accountMapper.selectOne(account);

        if (user == null) {

            String uuid = JwtUtil.randomUuID();
            String password = account.getPassword();
            Date now = new Date();

            account.setUuid(uuid);
            account.setPassword(ShiroAESUtil.encrypt(password));
            account.setCreatedatetime(now);
            account.setUpdatedatetime(now);
            Integer result = accountMapper.insert(account);

            if (result != 1) {
                return new ResponseBean(400, "添加失败", null);
            }

            return new ResponseBean(200, "添加成功", null);
        } else {
            return new ResponseBean(400, "用户已存在", null);
        }

    }

    @PutMapping("{accountID}")
    public ResponseBean updateAccount(@PathVariable Integer accountID, @RequestBody Account account) {

        account.setAccount(accountID);

        String password = ShiroAESUtil.encrypt(account.getPassword());

        account.setPassword(password);

        Integer result = accountMapper.updateByPrimaryKeySelective(account);

        if (result != 1) {

            return new ResponseBean(400, "更新失败", null);
        }
        return new ResponseBean(200, "更新成功", null);
    }

    @DeleteMapping("{accountID}")
    public ResponseBean deleteAccount(@PathVariable Integer accountID) {

        Account account = new Account();

        account.setAccount(accountID);

        Integer result = accountMapper.deleteByPrimaryKey(account);

        if (result != 1) {

            return new ResponseBean(400, "删除失败", null);

        }
        return new ResponseBean(200, "删除成功", null);
    }

    @GetMapping("online")
    public ResponseBean onlineState() {

        Set<String> member = stringRedisTemplate.keys(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + "*");
        return new ResponseBean(200, String.valueOf(member.size()), null);
    }

    @DeleteMapping("down/{accountID}")
    public ResponseBean onlineDown(@PathVariable Integer accountID) {

        Account account = new Account();

        account.setAccount(accountID);

        Account user = accountMapper.selectOne(account);

        if (user == null) {
            return new ResponseBean(400, "用户不存在", null);
        }

        boolean userIsOnline = stringRedisTemplate.hasKey(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + user.getUuid());

        if (userIsOnline) {

            stringRedisTemplate.delete(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + user.getUuid());

            boolean result = stringRedisTemplate.hasKey(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + user.getUuid());

            if (result) {
                return new ResponseBean(400, "下线失败", null);
            }

            return new ResponseBean(400, "下线成功", null);
        }

        return new ResponseBean(400, "用户不在线", null);

    }

    @RequiresRoles("教师")
    @GetMapping("article")
    public ResponseBean article() {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "您已经登录了(You are already logged in)",  null);
        } else {
            return new ResponseBean(200, "你是游客(You are guest)",  null);
        }
    }

    @RequiresAuthentication
    @GetMapping("article2")
    public ResponseBean article2() {

        return new ResponseBean(200, "您已经登录了(You are already logged in)", null);
    }

    @Cacheable(value = "cachePage")
    @GetMapping("page")
    public ResponseBean rolepage(Integer id) {

        System.out.println("id = " + id);

        Page page = PageHelper.startPage(1, 6);

        List<Role> role = roleMapper.selectAll();

        return new ResponseBean(200, "成功", (Page<Role>) role);

    }

    @Cacheable(value = "cachePage")
    @GetMapping("page3")
    public ResponseBean rolepage3() {

        Page page = PageHelper.startPage(1, 6);

        List<Role> role = roleMapper.selectAll();

        return new ResponseBean(200, "成功", (Page<Role>) role);

    }

    @Cacheable(value = "cachePage2", key = "#account.account")
    @PostMapping("page2")
    public ResponseBean rolepage2(@RequestBody Account account) {

        Page page = PageHelper.startPage(3, 6);

        List<Role> role = roleMapper.selectAll();

        return new ResponseBean(200, "成功", role);

    }
}
