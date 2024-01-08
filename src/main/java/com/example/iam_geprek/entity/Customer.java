package com.example.iam_geprek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "gender", nullable = false, length = 100)
    private String gender;
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Column(name = "mobile_phone", unique = true, nullable = false, length =30 )
    private String phone;
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "customer_code")
    private String customerCode;
}
