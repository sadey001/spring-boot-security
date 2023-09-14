package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Customer;
import com.spring.security.entity.CustomerResponse;
import com.spring.security.service.CustomerService;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String test() {
		return "testing";
	}
	
	@PostMapping("/register")
	public ResponseEntity<CustomerResponse> loginUser(@RequestBody Customer customer) {
		String encode = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encode);
		Customer insertCustomer = customerService.insertCustomer(customer);
		return new ResponseEntity<>(new CustomerResponse("Customer Created Successfully",202,insertCustomer),HttpStatus.CREATED);	
	}
	
	
}
