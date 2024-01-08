package com.example.iam_geprek.controller;

import com.example.iam_geprek.constant.Path;
import com.example.iam_geprek.dto.request.CustomerRequest;
import com.example.iam_geprek.dto.request.RestoRequest;
import com.example.iam_geprek.dto.response.RestoResponse;
import com.example.iam_geprek.service.RestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Path.RESTAURANT)
public class RestoController {

    private final RestoService restoService;

    @PostMapping("/create")
    public RestoResponse createResto(@RequestBody RestoRequest restoRequest) {
        return restoService.create(restoRequest);
    }

    @GetMapping("/{id}")
    public RestoResponse getDataById(@PathVariable String id) {
        return restoService.getById(id);
    }

    @GetMapping("/getall")
    public List<RestoResponse> getAll() {
        return restoService.getAll();
    }

    @PutMapping("/update")
    public RestoResponse updateResto(@RequestBody RestoRequest restoRequest) {
        return restoService.update(restoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteResto(@PathVariable String id) {
        restoService.delete(id);
    }
}
