package com.talent.front.util.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.talent.front.dto.SysUserDto;
import com.talent.front.util.redis.RedisUtil;

@Component
public class UserCurrent {
    private static final Logger logger = LoggerFactory.getLogger(UserCurrent.class);

    private volatile static UserCurrent instance = null;

    private static RedisUtil redisUtil;

    @Autowired(required = true)
    public void setUserAccessor(RedisUtil ru) {
        redisUtil = ru;
    }

    private UserCurrent() {
    }

    // Java 中的双重检查
    public static UserCurrent getInstance() {
        if (instance == null) {
            synchronized (UserCurrent.class) {
                if (instance == null)
                    instance = new UserCurrent();
            }
        }
        return instance;
    }

    public SysUserDto getSysUserDto() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String accessToken = AccessTokenVerifyInterceptor.getToken(request);
        Object object = redisUtil.get(accessToken);
        if (object != null) {
            if (object instanceof SysUserDto) {
                SysUserDto sysUserDto = (SysUserDto) object;
                logger.info("得到当前用户：" + sysUserDto.toString());
                return sysUserDto;
            }
        }
        return null;
    }

    public void setUserId(Object object) {
        try {
            SysUserDto sysUserDto = getSysUserDto();
            if (sysUserDto != null) {
                String user_id = sysUserDto.getUserId();

                Class cla = object.getClass();
                Method method = cla.getMethod("setCreator", String.class);
                method.invoke(object, new Object[] { user_id });
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("对象付用户id错误。。。");
        }
    }

    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String accessToken = AccessTokenVerifyInterceptor.getToken(request);
        if (accessToken != null) {
            redisUtil.del(accessToken);
        }
    }

}
