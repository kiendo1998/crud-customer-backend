package com.kiendo.customer.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kiendo.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Page<Customer> findAll(Pageable pageable);
}
