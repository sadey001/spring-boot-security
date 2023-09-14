package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.entity.Customer;
import com.spring.security.entity.Role;
import com.spring.security.repository.CustomerRepository;
import com.spring.security.repository.RoleRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user;
		List<Customer> customers = customerRepo.findByEmail(username);
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		if(customers.size()==1) {
			Customer customer = customers.get(0);
			
			List<Role> roles = roleRepo.findById(customer.getRole_id());
			for(Role role: roles) {
				auths.add(new SimpleGrantedAuthority(role.getName()));
			}
					
			user = User.withUsername(customer.getEmail())
					.password(customer.getPassword())
					.authorities(auths)
					.build();
			
		}else {
			throw new UsernameNotFoundException("Email not found : "+username);
		}
		
		return user;
	}
	
}
