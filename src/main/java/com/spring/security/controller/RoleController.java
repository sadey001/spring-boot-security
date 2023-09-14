package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Role;
import com.spring.security.entity.RoleResponse;
import com.spring.security.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/roles")
	public ResponseEntity<RoleResponse> getRoles(){
		List<Role> roles  = roleService.getAllRoles();
		RoleResponse rs = new RoleResponse();
		if(roles != null && roles.size()>0) {
			rs.setStatusCode(200);
			rs.setMsg("Successful");
			rs.setRoles(roles);
			return new ResponseEntity<>(rs,HttpStatus.OK);
		}else {
			rs.setStatusCode(205);
			rs.setMsg("No User Found");
			rs.setRoles(roles);
			return new ResponseEntity<>(rs,HttpStatus.OK);
		}
	}

}
