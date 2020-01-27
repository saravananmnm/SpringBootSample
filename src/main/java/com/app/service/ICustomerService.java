package com.app.service;

import java.util.List;

import com.app.model.Customer;

public interface ICustomerService {
	public Customer saveCustomer(Customer c);
	public Customer getCustomerById(Integer id);
	public List<Customer> getAllCustomers();
	public void deleteCustomerById(Integer id);
	public boolean CustomerExist(Integer id);
}
