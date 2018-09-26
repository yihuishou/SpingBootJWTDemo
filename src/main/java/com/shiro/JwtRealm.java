package com.shiro;

import com.mapper.AccountMapper;
import com.model.Account;
import com.vo.UserPermission;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by LadyLady on 2018-09-12.
 */
@Component
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Account account = (Account) principalCollection.getPrimaryPrincipal();

        UserPermission userPermission = accountMapper.findUserPermissionByUuid(account.getUuid());

        simpleAuthorizationInfo.setRoles(userPermission.getRoleSet());

        simpleAuthorizationInfo.setStringPermissions(userPermission.getPowerSet());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = authenticationToken.getCredentials().toString();

        String uuid = JwtUtil.getUuID(token);

        if (uuid == null) {

            throw new AuthenticationException("Token中帐号为空 (The account in Token is empty.)");
        }

        Account account = new Account();

        account.setUuid(uuid);

        account = accountMapper.selectOne(account);

        if (account == null) {

            throw new AuthenticationException("用户不存在 (User didn't existed!)");

        }

        JwtUtil.verify(token);

        if (stringRedisTemplate.hasKey(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid)) {

            String createTokenTimeInRedis = stringRedisTemplate.opsForValue().get(ShiroEnum.PREFIX_SHIRO_REFRESH_TOKEN + uuid);
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getCreateTime(token).equals(createTokenTimeInRedis)) {

                return new SimpleAuthenticationInfo(account, token, this.getName());

            }
        }

        throw new AuthenticationException("Token签名验证失败 (Token expired or incorrect.)");
    }
}
