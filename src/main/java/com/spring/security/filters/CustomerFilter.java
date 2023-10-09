package com.spring.security.filters;

import java.io.IOException;
import java.lang.System.Logger;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Deprecated
public class CustomerFilter implements Filter {
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Request Comming From :"+request.getRemoteAddr());
		System.out.println("Request Content type :"+response.getContentType());
		
		chain.doFilter(request, response);
	}

}
