package com.example.iam_geprek.service;

import com.example.iam_geprek.dto.request.CustomerRequest;
import com.example.iam_geprek.dto.response.CustomerResponse;

import java.util.List;


public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse getById (String id);
    List<CustomerResponse> getAll();
    CustomerResponse update (CustomerRequest customerRequest);
    void delete(String id);
}
