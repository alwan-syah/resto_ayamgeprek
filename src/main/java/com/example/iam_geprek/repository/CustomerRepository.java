package com.example.iam_geprek.repository;

import com.example.iam_geprek.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByCustomerCode(String customerCode);
}
