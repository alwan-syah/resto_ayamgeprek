package com.example.iam_geprek.service.impl;

import com.example.iam_geprek.dto.request.CustomerRequest;
import com.example.iam_geprek.dto.response.CustomerResponse;
import com.example.iam_geprek.entity.Customer;
import com.example.iam_geprek.repository.CustomerRepository;
import com.example.iam_geprek.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .gender(customerRequest.getGender())
                .address(customerRequest.getAddress())
                .phone(customerRequest.getPhone())
                .email(customerRequest.getEmail())
                .build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .nameCustomer(customer.getName())
                .gender(customer.getGender())
                .addressCustomer(customer.getAddress())
                .phoneCustomer(customer.getPhone())
                .emailCustomer(customer.getEmail())
                .build();
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .nameCustomer(customer.getName())
                    .gender(customer.getGender())
                    .addressCustomer(customer.getAddress())
                    .phoneCustomer(customer.getPhone())
                    .emailCustomer(customer.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .nameCustomer(customer.getName())
                        .addressCustomer(customer.getAddress())
                        .phoneCustomer(customer.getPhone())
                        .build())
                .collect(Collectors.toList());
        return customerResponses;
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerRequest.getId());
        if (optionalCustomer.isPresent()) {
            Customer customer = Customer.builder()
                    .id(customerRequest.getId())
                    .name(customerRequest.getName())
                    .gender(customerRequest.getGender())
                    .address(customerRequest.getAddress())
                    .phone(customerRequest.getPhone())
                    .email(customerRequest.getEmail())
                    .build();
            customerRepository.save(customer);
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .nameCustomer(customer.getName())
                    .gender(customer.getGender())
                    .addressCustomer(customer.getAddress())
                    .phoneCustomer(customer.getPhone())
                    .emailCustomer(customer.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.deleteById(id);
            System.out.println("Delete Success");
        } else {
            System.out.println("id not found");
        }
    }
}
