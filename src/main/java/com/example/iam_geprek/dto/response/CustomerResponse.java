package com.example.iam_geprek.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private String id;
    private String nameCustomer;
    private String gender;
    private String addressCustomer;
    private String phoneCustomer;
    private String emailCustomer;
}
