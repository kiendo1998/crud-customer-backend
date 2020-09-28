package com.kiendo.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kiendo.customer.dao.CustomerRepository;
import com.kiendo.customer.entity.Customer;


@Service
public class CustomerServiceImplement implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImplement(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}
	
	@Override
	public Page<Customer> findAll(Pageable page) {
		return customerRepository.findAll(page);
	}

	@Override
	public Customer findById(int theId) {
		Optional<Customer> result = customerRepository.findById(theId);
		
		Customer theCustomer = null;
		
		if (result.isPresent()) {
			theCustomer = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		customerRepository.save(theCustomer);
	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);
	}

}

