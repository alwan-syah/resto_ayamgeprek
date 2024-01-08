package com.example.iam_geprek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RestoRequest {
    private String id;
    private String noIzin;
    private String name;
    private String address;
    private String phone;
}
