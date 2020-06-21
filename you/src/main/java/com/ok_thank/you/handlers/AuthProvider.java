package com.ok_thank.you.handlers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ok_thank.you.dto.Member;
import com.ok_thank.you.service.SecurityService;
 
@Component
public class AuthProvider implements AuthenticationProvider{
    
    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
    
    @Autowired
    SecurityService securityService;
 
    //로그인 버튼을 누를 경우
 
    //첫번째 실행
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }
    
    //두번쨰 실행
    private Authentication authenticate(String id, String password) throws AuthenticationException{
        
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        
        Member member = new Member();
            
        member = (Member)securityService.loadUserByUsername(id);
    
        if ( member == null ){
            logger.info("사용자 정보가 없습니다.");
            throw new UsernameNotFoundException(id);
        }else if(member != null && !member.getPassword().equals(password) ) {
            logger.info("비밀번호가 틀렸습니다.");
            throw new BadCredentialsException(id);
        }
    
        grantedAuthorityList.add(new SimpleGrantedAuthority(member.getUserRole()));
        
        return new MyAuthentication(id, password, grantedAuthorityList, member);
 
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
 
}


