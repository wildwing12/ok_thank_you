package com.ok_thank.you.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException ade) throws IOException, ServletException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		if(ade instanceof AccessDeniedException) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			// TODO Auto-generated method stub
			 log.info("Exceiption : {}",ade);
			 log.info("LocalizedMessage : {}",ade.getLocalizedMessage());
			 log.info("Message : {}",ade.getMessage());
			 log.info("StackTrace : {}",ade.getStackTrace());
			  
			 request.setAttribute("errMsg","erro");
			 request.getRequestDispatcher("/WEB-INF/jsp/login/denied.jsp").forward(request, response);



		}
	}

}
