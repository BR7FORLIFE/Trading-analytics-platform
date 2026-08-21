package com.files.persistance.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.files.persistance.dtos.SaveEconomicDataRequestDto;
import com.files.persistance.models.DataEconomicDraft;
import com.files.persistance.services.EconomicDataService;
import com.files.persistance.shared.GetAll;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/economic")
public class EconomicDataController {

    private final EconomicDataService economicDataService;

    public EconomicDataController(EconomicDataService economicDataService) {
        this.economicDataService = economicDataService;
    }

    @PostMapping
    Mono<ResponseEntity<Void>> saveEconomicData(@RequestBody SaveEconomicDataRequestDto request) {
        return economicDataService.saveEconomicData(request).thenReturn(ResponseEntity.ok().build());
    }

    @GetMapping
    public Mono<ResponseEntity<GetAll<DataEconomicDraft>>> getAllEconomicData(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "create_at", required = false) Instant createAt) {
        return economicDataService.getAllinfo(page, limit, createAt)
                .map(res -> ResponseEntity.ok().body(res));
    }
}
