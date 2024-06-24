package com.mruruc.customersurvey.repository;

import com.mruruc.customersurvey.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
}
