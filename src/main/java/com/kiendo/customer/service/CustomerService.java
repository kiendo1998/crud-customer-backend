package com.kiendo.customer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kiendo.customer.entity.Customer;

public interface CustomerService {
	public Page<Customer> findAll(Pageable page);

	public Customer findById(int theId);

	public void save(Customer theEmployee);

	public void deleteById(int theId);

}



