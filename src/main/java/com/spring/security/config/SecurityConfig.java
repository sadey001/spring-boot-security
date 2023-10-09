package com.spring.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.security.filters.CustomerFilter;
import com.spring.security.filters.JwtAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.addFilterAfter(new JwtAuthenticationFilter(),BasicAuthenticationFilter.class);
		http.authorizeHttpRequests()
		.requestMatchers("/api/roles/**").hasRole("ADMIN")
		.requestMatchers("/api/customers/**").hasAnyRole("ADMIN","USER")
		.requestMatchers("/api/test","/api/register").permitAll()
		.anyRequest().authenticated();
		http.formLogin().and().httpBasic();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		

		return http.build();
	}
	

	
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
