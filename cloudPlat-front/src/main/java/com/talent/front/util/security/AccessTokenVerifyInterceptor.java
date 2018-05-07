package com.talent.front.util.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.talent.front.dao.SysResourceDao;
import com.talent.front.dto.SysResourceDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.SysUserService;

@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysResourceDao sysResourceDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("AccessToken executing ...");
        boolean flag = false;
        // token
        String accessToken = getToken(request);

        if (!StringUtils.isEmpty(accessToken)) {
            SysUserDto sysUserDto = sysUserService.getDtoByToken(accessToken);
            if (sysUserDto != null) {
                List<SysResourceDto> permissions = sysResourceDao.findResourceByUserid(sysUserDto.getUserId());
                for (SysResourceDto permission : permissions) {
                    if (permission != null && permission.getResourceUrl() != null) {
                        AntPathMatcher matcher = new AntPathMatcher();
                        boolean result = matcher.match(permission.getResourceUrl(), request.getServletPath());
                        if (result) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    request.getRequestDispatcher("/deny").forward(request, response);
                }
            } else { // 用户不存在
                request.getRequestDispatcher("/relogin").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/relogin").forward(request, response);
        }

        return flag;

    }

    public static String getToken(HttpServletRequest request) {
        String accessToken = request.getParameter("token");
        if (StringUtils.isEmpty(accessToken)) {
            accessToken = request.getHeader("token");
            if (StringUtils.isEmpty(accessToken)) {
                accessToken = request.getSession().getId();
            }
        }
        return accessToken;
    }
}
