package com.ok_thank.you.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ok_thank.you.dto.LoginLog;
import com.ok_thank.you.dto.Member;
import com.ok_thank.you.mapper.LoginMapper;
import com.ok_thank.you.service.SecurityService;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Service
public class UserServiceImpl implements SecurityService {
    
    @Autowired
    LoginMapper loingMapper;
    
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
         Member member = loingMapper.getSelectMeberInfo(id);//데이터 베이스에서 정보 출력
         List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
         if(member != null) {
             authorities.add(new SimpleGrantedAuthority(member.getUserRole()));//member에서 가져온 권한을 스프링시큐리티에 넣어줌
             member.setAuthorities(authorities);//그걸 다시 권한으로 집어 넣음
         }
         return member;
    }
    
    @Override
    public int setInsertMember(Member member) throws Exception{
    	member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        return loingMapper.setInsertMember(member);
    }
 
    @Override
    public Member getSelectMeberInfo(String id) throws Exception{
        return loingMapper.getSelectMeberInfo(id);
    }
    
    @Override
    public int setUpdatePasswordLockCnt(String id) throws Exception{
        return loingMapper.setUpdatePasswordLockCnt(id);
    }
    
    @Override
    public int setUpdatePasswordLockCntReset(String id) throws Exception{
        return loingMapper.setUpdatePasswordLockCntReset(id);
    }

	@Override
	public int setInsertLoginLog(LoginLog loginLog) throws Exception {
		return loingMapper.setInsertLoginLog(loginLog);
	}

	@Override
	public Member infoMember(HttpSession session, String id) {
    	Member member = loingMapper.getSelectMeberInfo(id);
    	if(member != null) {
    		log.info("유저 이름은 ={}", member.getMemberName());
    		session.setAttribute("member", member);
    	}
		return member;
	}
 
}


