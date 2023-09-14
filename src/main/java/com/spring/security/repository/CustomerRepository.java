package com.spring.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer , Integer> {
	
	List<Customer> findAll();
	List<Customer> findById(int id);
	List<Customer> findByEmail(String email);

}
