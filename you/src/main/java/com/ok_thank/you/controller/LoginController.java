package com.ok_thank.you.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.Member;
import com.ok_thank.you.service.SecurityService;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Controller
public class LoginController {
    
    @Autowired
    SecurityService securityService;
    
    //회원가입 페이지 이동
    @GetMapping("/join/join")
    public String join() {
        return  "login/join";
    }
    
    //로그인 페이지 이동
    @RequestMapping("/login/login")
    public ModelAndView login(@RequestParam(value="msg", required=false) String msg) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("/login/login");
        return mv;
    }
    
    //로그인 에러페이지 이동
    @GetMapping("/login/login-error")
    public String error() {
        return  "login/error";
    }
    
    //아이디 중복체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam String inputId) throws Exception{
    	String canUse = "";
    	//입력 아이디 null체크(빈값이거나 null일 경우 keyup 이벤트에서 체크 완료되지 않도록)
    	if(StringUtils.isNotEmpty(inputId)) {
    		Member member = securityService.getSelectMeberInfo(inputId);
            canUse = member != null ? "" : "Y";
    	}
    	return canUse;
    }
    
    //회원가입 Insert 
    @PostMapping("/join/insert")
    public String setInsertMember(Member member) throws Exception{
        if(securityService.setInsertMember(member) > 0){
            return  "login/login";    
        }else {
            return  "join/join";
        }
    }
    
    //로그인 성공후 이동페이지
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	//System.out.println(">>>>>>>>>>>>>>>>>>"+user.getAuthorities());
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String id = auth.getName();
    	Member member= securityService.infoMember(session,id);
        return  "home/home";
    }
    
    //권한이 없는 사람 페이지 이동
    @GetMapping("/user/denied")
    public String denied(Model model) {// AuthenticateAction auth, HttpServletRequest req) {
    	// 권한 없는 사용자가 접근을 하게 되면 security에서 해당 request에 AccessDeniedException을 전달 하는데 속성명은WebAttributes.ACCESS_DENIED_403으로 전달한다.
    	//AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
    	/*model.addAttribute("auth","성공");
    	model.addAttribute("errMsg","하다");*/
    	return "login/denied";
    }
    
}


