package com.spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;
import com.spring.security.repository.RoleRepository;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	CustomerRepository repo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		

		List<Customer> customers = repo.findByEmail(username);
		
		
		
		if(customers.size()>0) {
			List<GrantedAuthority> auths = new ArrayList<>();
			auths.add(new SimpleGrantedAuthority(roleRepo.findById(customers.get(0).getRole_id()).get(0).getName()));
			
			if(username.contains("@tcs.com")) {
				return new UsernamePasswordAuthenticationToken(username, password, auths);
			}
			if(passwordEncoder.matches(password,customers.get(0).getPassword())) {
				
				Authentication auth= new UsernamePasswordAuthenticationToken(username, password, auths);
				SecurityContextHolder.getContext().setAuthentication(auth);
				return auth;
			}else {
				throw new BadCredentialsException("Invalid credentials");
			}
		}else{
			throw new UsernameNotFoundException("Username not found");
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	
	
}
