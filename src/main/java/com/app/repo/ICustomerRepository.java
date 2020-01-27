package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
