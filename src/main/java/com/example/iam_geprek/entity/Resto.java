package com.example.iam_geprek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="m_resto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Resto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "no_izin", unique = true, nullable = false, length = 30)
    private String noIzin;
    @Column(name = "name",  nullable = false, length = 100)
    private String name;
    @Column(name = "address",  nullable = false, length = 100)
    private String address;
    @Column(name = "mobile_phone", unique = true, nullable = false, length = 30)
    private String phone;
    @Column(name = "resto_code")
    private String restoCode;
}
