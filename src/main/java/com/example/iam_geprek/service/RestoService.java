package com.example.iam_geprek.service;

import com.example.iam_geprek.dto.request.RestoRequest;
import com.example.iam_geprek.dto.response.RestoResponse;

import java.util.List;

public interface RestoService {
    RestoResponse create(RestoRequest restoRequest);
    RestoResponse getById (String id);
    List<RestoResponse> getAll();
    RestoResponse update (RestoRequest restoRequest);
    void delete(String id);
}
