package com.spring.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.security.entity.Role;

public interface RoleRepository extends CrudRepository<Role ,Integer>{
	
	List<Role> findAll();
	List<Role> findById(int id);
	List<Role> findByName(String Name);

}
