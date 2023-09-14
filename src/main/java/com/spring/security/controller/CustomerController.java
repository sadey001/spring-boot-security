package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Customer;
import com.spring.security.entity.CustomerResponse;
import com.spring.security.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@GetMapping("/customers")
	public ResponseEntity<CustomerResponse> getAllCustomers(){
		CustomerResponse cr = new CustomerResponse();
		List<Customer> customers = service.getAllCustomer();
		
		if(customers != null && customers.size()>0) {
			cr.setStatusCode(200);
			cr.setMsg("Successful");
			cr.setCustomers(customers);
		}else {
			cr.setStatusCode(408);
			cr.setMsg("No customer found!");
		}
		
		return new ResponseEntity<CustomerResponse>(cr, HttpStatus.OK);	
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable int id){
		List<Customer> customers = service.getCustomer(id);
		CustomerResponse cr = new CustomerResponse();
		
		if(customers != null && customers.size()>0) {
			cr.setStatusCode(200);
			cr.setMsg("Successfull");
			cr.setCustomers(customers);
		}else {
			cr.setStatusCode(209);
			cr.setMsg("No customer found");
		}
		return new ResponseEntity<>(cr, HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable int id){
		
		try {
			service.deleteCustomer(id);
			return new ResponseEntity<CustomerResponse>(new CustomerResponse("Deleted Successfully",200,new ArrayList<>()),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<CustomerResponse>(new CustomerResponse("Deletion Failed",500,new ArrayList<>()),HttpStatus.EXPECTATION_FAILED);
		}
		
		
		
	}

}
