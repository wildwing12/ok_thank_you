package com.ok_thank.you.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ok_thank.you.dto.LoginLog;
import com.ok_thank.you.dto.Member;
 
public interface SecurityService extends UserDetailsService {
    // 시큐리티 사용자 인증
    UserDetails loadUserByUsername(String id);
    // 중복아이디 체크
    Member getSelectMeberInfo(String id) throws Exception;
    //회원가입
    int setInsertMember(Member member)throws Exception;
    // 비밀번호 틀린 횟수 증가
    int setUpdatePasswordLockCnt(String id) throws Exception;
    // 비밀번호 틀린 횟수 초기화
    int setUpdatePasswordLockCntReset(String id) throws Exception;
    
    int setInsertLoginLog(LoginLog loginLog) throws Exception;
}


