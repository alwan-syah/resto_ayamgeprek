package com.example.iam_geprek.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RestoResponse {
    private String id;
    private String noIzinRestaurant;
    private String nameRestaurant;
    private String addressRestaurant;
    private String phoneRestaurant;
}
