package com.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.entity.Role;
import com.spring.security.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository repo;
	
	public List<Role> getAllRoles(){
		return repo.findAll();
	}
	
	public List<Role> getRole(int id){
		return repo.findById(id);
	}
	
	public List<Role> getRole(String name){
		return repo.findByName(name);
	}
	
	public Role insertRole(Role role) {
		return repo.save(role);
	}
	
	public void deleteRole(int id) {
		repo.deleteById(id);
	}
	
}
