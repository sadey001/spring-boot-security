package com.spring.security.entity;

import java.util.ArrayList;
import java.util.List;

public class CustomerResponse {
	
	private String msg;
	private int statusCode;
	private List<Customer> customers;
	public CustomerResponse(String msg, int statusCode, List<Customer> customers) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
		this.customers = customers;
	}
	public CustomerResponse(String msg, int statusCode, Customer customers) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
		this.customers = new ArrayList<>();
		this.customers.add(customers);
	}
	public CustomerResponse() {
		super();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public void setCustomers(Customer customers) {
		this.customers.add(customers);
	}
	
	
	

}
