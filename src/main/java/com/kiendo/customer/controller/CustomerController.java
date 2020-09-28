package com.kiendo.customer.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiendo.customer.entity.Customer;
import com.kiendo.customer.service.CustomerService;
@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
@RequestMapping("/api")

public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
//	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/customers")
	public Page<Customer> findAll(@RequestParam int page) {
		Page<Customer> pageNum = customerService.findAll(PageRequest.of(page, 5));
		return pageNum;
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.findById(customerId);
		
		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}
		
		return theCustomer;
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theCustomer.setId(0);
		
		customerService.save(theCustomer);
		
		return theCustomer;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.save(theCustomer);
		
		return theCustomer;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.findById(customerId);
		
		// throw exception if null
		
		if (tempCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}
		
		customerService.deleteById(customerId);
		
		return "Deleted Customer id - " + customerId;
	}
	
}
