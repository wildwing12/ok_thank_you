package com.ok_thank.you.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ok_thank.you.dto.LoginLog;
import com.ok_thank.you.service.SecurityService;
import com.ok_thank.you.util.CommonUtil;
 
/**
 * 로그인 실패 핸들러
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler  {
    
    @Autowired
    SecurityService securityService;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
    
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        
        String ip = CommonUtil.getClientIp(request);
        logger.info(" :::::::::::::::::::::::::::: 로그인실패 :::::::::::::::::::::::: ");
        LoginLog loginLog = new LoginLog();
        String id = "";
        String msg = "";
        
        try {
            
            id = exception.getMessage();
            
            if(securityService.getSelectMeberInfo(id) != null) {
                securityService.setUpdatePasswordLockCnt(id);
                loginLog.setLoginIp(ip);
                loginLog.setId(id);
                loginLog.setStatus("FAILD");
                securityService.setInsertLoginLog(loginLog);
               msg="error";
            }else {
               msg="error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/login/login?msg="+msg);
    }
 
}


