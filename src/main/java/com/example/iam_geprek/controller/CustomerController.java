package com.example.iam_geprek.controller;

import com.example.iam_geprek.constant.Path;
import com.example.iam_geprek.dto.request.CustomerRequest;
import com.example.iam_geprek.dto.response.CustomerResponse;
import com.example.iam_geprek.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Path.CUSTOMER)
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public CustomerResponse createCustomers(@RequestBody CustomerRequest customerRequest) {
        return customerService.create(customerRequest);
    }

    @GetMapping("/{id}")
    public CustomerResponse getDataById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @GetMapping("/getall")
    public List<CustomerResponse> getAll() {
        return customerService.getAll();
    }

    @PutMapping("/update")
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.update(customerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
    }
}
