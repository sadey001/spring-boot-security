package com.spring.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.config.CustomerAuthenticationProvider;
import com.spring.security.utility.JwtUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JwtUtility util= new JwtUtility();
		
		if(null != auth) {
			String token = util.generateJwtToke(auth.getName(), auth.getAuthorities());
			System.out.println(token);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(auth.getName(),token,auth.getAuthorities()));
			response.setHeader("token", token);
		}else {
			response.sendError(401);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
