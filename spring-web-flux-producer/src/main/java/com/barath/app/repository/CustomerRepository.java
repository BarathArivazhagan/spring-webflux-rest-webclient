package com.barath.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barath.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByCustomerName(String customerName);
}
