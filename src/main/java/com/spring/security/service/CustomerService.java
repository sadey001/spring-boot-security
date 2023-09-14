package com.spring.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository repo;
	
	public List<Customer> getAllCustomer(){
		return repo.findAll();
	}
	
	public Customer insertCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public List<Customer> getCustomer(int id) {
		return repo.findById(id);
	}
	
	public void deleteCustomer(int id) {
		repo.deleteById(id);
	}

}
