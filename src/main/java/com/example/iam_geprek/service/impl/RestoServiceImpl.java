package com.example.iam_geprek.service.impl;

import com.example.iam_geprek.dto.request.RestoRequest;
import com.example.iam_geprek.dto.response.RestoResponse;
import com.example.iam_geprek.entity.Resto;
import com.example.iam_geprek.repository.RestoRepository;
import com.example.iam_geprek.service.RestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestoServiceImpl implements RestoService {
    private final RestoRepository restoRepository;

    @Override
    public RestoResponse create(RestoRequest restoRequest) {
        Resto resto = Resto.builder()
                .noIzin(restoRequest.getNoIzin())
                .name(restoRequest.getName())
                .address(restoRequest.getAddress())
                .phone(restoRequest.getPhone())
                .build();
        restoRepository.save(resto);
        return RestoResponse.builder()
                .nameRestaurant(resto.getName())
                .addressRestaurant(resto.getAddress())
                .addressRestaurant(restoRequest.getAddress())
                .phoneRestaurant(restoRequest.getPhone())
                .build();
    }

    @Override
    public RestoResponse getById(String id) {
        Resto resto = restoRepository.findById(id).orElse(null);
        if (resto != null) {
            RestoResponse.builder()
                    .id(resto.getId())
                    .noIzinRestaurant(resto.getNoIzin())
                    .nameRestaurant(resto.getName())
                    .addressRestaurant(resto.getAddress())
                    .phoneRestaurant(resto.getPhone())
                    .build();
        }
        return null;
    }

    @Override
    public List<RestoResponse> getAll() {
        List<Resto> restos = restoRepository.findAll();
        List<RestoResponse> restoResponses = restos.stream()
                .map(resto -> RestoResponse.builder()
                        .noIzinRestaurant(resto.getNoIzin())
                        .nameRestaurant(resto.getName())
                        .addressRestaurant(resto.getAddress())
                        .phoneRestaurant(resto.getPhone())
                        .build())
                .collect(Collectors.toList());
        return restoResponses;
    }

    @Override
    public RestoResponse update(RestoRequest restoRequest) {
        Optional<Resto> optionalResto = restoRepository.findById(restoRequest.getId());
        if (optionalResto.isPresent()) {
            Resto resto = Resto.builder()
                    .id(restoRequest.getId())
                    .noIzin(restoRequest.getNoIzin())
                    .name(restoRequest.getName())
                    .address(restoRequest.getAddress())
                    .phone(restoRequest.getPhone())
                    .build();
            restoRepository.save(resto);
            return RestoResponse.builder()
                    .id(resto.getId())
                    .noIzinRestaurant(resto.getNoIzin())
                    .nameRestaurant(resto.getName())
                    .addressRestaurant(resto.getAddress())
                    .phoneRestaurant(resto.getPhone())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        Resto resto = restoRepository.findById(id).orElse(null);
        if (resto != null){
            restoRepository.deleteById(id);
            System.out.println("Delete Success");
        }else {
            System.out.println("id not found");
        }
    }
}
