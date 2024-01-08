package com.example.iam_geprek.service.impl;

import com.example.iam_geprek.dto.request.CustomerRequest;
import com.example.iam_geprek.dto.response.CustomerResponse;
import com.example.iam_geprek.entity.Customer;
import com.example.iam_geprek.repository.CustomerRepository;
import com.example.iam_geprek.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @PersistenceContext
    private EntityManager em;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        String nativeQuery = "INSERT INTO m_customer(id,name,gender,address,mobile_phone,email,customer_code)" + "VALUES(?,?,?,?,?,?,?)";
        em.createNativeQuery(nativeQuery)
                .setParameter(1, UUID.randomUUID().toString())
                .setParameter(2, customerRequest.getName())
                .setParameter(3, customerRequest.getGender())
                .setParameter(4, customerRequest.getAddress())
                .setParameter(5, customerRequest.getPhone())
                .setParameter(6, customerRequest.getEmail())
                .setParameter(7, customerRequest.getCustomerCode())
                .executeUpdate();

        Customer customer = customerRepository.findByCustomerCode(customerRequest.getCustomerCode());
        return CustomerResponse.builder()
                .id(customer.getId())
                .nameCustomer(customer.getName())
                .gender(customer.getGender())
                .addressCustomer(customer.getAddress())
                .phoneCustomer(customer.getPhone())
                .emailCustomer(customer.getEmail())
                .customerCode(customer.getCustomerCode())
                .build();
    }

    @Override
    public CustomerResponse getById(String id) {
        String nativeQuery = "SELECT id, name, gender,address,mobile_phone,email FROM m_customer WHERE id = ?";
        Query query = em.createNativeQuery(nativeQuery)
                .setParameter(1, id);
        List<Object[]> results = query.getResultList();
        Object[] result = results.get(0);
        if (result != null) {
            return CustomerResponse.builder()
                    .id((String) result[0])
                    .nameCustomer((String) result[1])
                    .gender((String) result[2])
                    .addressCustomer((String) result[3])
                    .phoneCustomer((String) result[4])
                    .emailCustomer((String) result[5])
                    .build();
        }
        return null;
    }

    @Override
    public List<CustomerResponse> getAll() {
        String nativeQuery = "SELECT id, name, address, mobile_phone FROM m_customer";
        Query query = em.createNativeQuery(nativeQuery);
        List<Object[]> results = query.getResultList();
        List<CustomerResponse> customerResponses = results.stream()
                .map(result -> CustomerResponse.builder()
                        .id((String) result[0])
                        .nameCustomer((String) result[1])
                        .addressCustomer((String) result[2])
                        .phoneCustomer((String) result[3])
                        .build())
                .collect(Collectors.toList());
        return customerResponses;
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        Query findCustomerQuery = em.createNativeQuery("SELECT * FROM m_customer WHERE id = ?", Customer.class)
                .setParameter(1, customerRequest.getId());
        List<Customer> resultList = findCustomerQuery.getResultList();
        if (!resultList.isEmpty()) {
            Query queryUpdate = em.createNativeQuery("UPDATE m_customer SET  name=?,gender=?,address=?, mobile_phone=?, email=?, customer_code =? WHERE id=?")
                    .setParameter(1, customerRequest.getName())
                    .setParameter(2, customerRequest.getGender())
                    .setParameter(3, customerRequest.getAddress())
                    .setParameter(4, customerRequest.getPhone())
                    .setParameter(5, customerRequest.getEmail())
                    .setParameter(6, customerRequest.getCustomerCode())
                    .setParameter(7, customerRequest.getId());
            queryUpdate.executeUpdate();

            return CustomerResponse.builder()
                    .id(customerRequest.getId())
                    .nameCustomer(customerRequest.getName())
                    .gender(customerRequest.getGender())
                    .addressCustomer(customerRequest.getAddress())
                    .phoneCustomer(customerRequest.getPhone())
                    .emailCustomer(customerRequest.getEmail())
                    .customerCode(customerRequest.getCustomerCode())
                    .build();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        try {
            Query nativeQuery = em.createNativeQuery("DELETE FROM m_customer WHERE id=?")
                    .setParameter(1, id);
            int deleteRow = nativeQuery.executeUpdate();
            if (deleteRow > 0) {
                System.out.println("Delete Success");
            } else {
                System.out.println("id not found");
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }
}
