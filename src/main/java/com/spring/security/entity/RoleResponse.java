package com.spring.security.entity;

import java.util.List;

public class RoleResponse {
	
	private int statusCode;
	private String msg;
	private List<Role> roles;
	public RoleResponse(int statusCode, String msg, List<Role> roles) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
		this.roles = roles;
	}
	public RoleResponse() {
		super();
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "RoleResponse [statusCode=" + statusCode + ", msg=" + msg + ", roles=" + roles + "]";
	}
	
	

}
