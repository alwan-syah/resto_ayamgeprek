package com.example.iam_geprek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerRequest {
    private String id;
    private String name;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String customerCode;
}
