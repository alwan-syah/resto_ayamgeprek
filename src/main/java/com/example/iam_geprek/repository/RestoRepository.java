package com.example.iam_geprek.repository;

import com.example.iam_geprek.entity.Customer;
import com.example.iam_geprek.entity.Resto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoRepository extends JpaRepository<Resto,String> {
    Resto findByRestoCode(String restoCode);
}
