package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.repo.ICustomerRepository;
import com.app.service.ICustomerService;

@Service
@EnableTransactionManagement
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerRepository repo;

	@Override
	@Transactional
	public Customer saveCustomer(Customer c) {
		Customer custm=repo.save(c);
		return custm;
	}

	@Override
	@Transactional(readOnly=true)
	public Customer getCustomerById(Integer id) {

		Customer cust=repo.findById(id).get();
		
		return cust;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Customer> getAllCustomers() {
		List<Customer> clist=repo.findAll();
		if(clist!=null && !clist.isEmpty()) {
			return clist;
		}
		else {
		return null;
		}
	}

	@Override
	@Transactional
	public void deleteCustomerById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean CustomerExist(Integer id) {
		return repo.existsById(id);
	}

}
