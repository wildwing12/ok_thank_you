package com.ok_thank.you.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginUrlAuth implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		    log.error("Responding with unauthorized error. Message - {}", authException.getMessage());
			 request.setAttribute("errMsg","erro");
			 request.getRequestDispatcher("/WEB-INF/jsp/login/denied.jsp").forward(request, response);
		
	}

}
