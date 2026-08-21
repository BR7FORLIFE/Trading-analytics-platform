package com.files.persistance.services;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.files.persistance.dtos.SaveEconomicDataRequestDto;
import com.files.persistance.models.DataEconomicDraft;
import com.files.persistance.repository.EconomicDataRepositoryAdapter;
import com.files.persistance.repository.entities.DataEconomicEntity;
import com.files.persistance.shared.GetAll;

import io.r2dbc.postgresql.codec.Json;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Service
public class EconomicDataService {

    private final EconomicDataRepositoryAdapter repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public EconomicDataService(EconomicDataRepositoryAdapter economicDataRepositoryAdapter) {
        this.repository = economicDataRepositoryAdapter;
    }

    public Mono<Void> saveEconomicData(SaveEconomicDataRequestDto data) {
        // escribimos la informacion que nos llega en un jsonB string
        List<DataEconomicDraft> economicDrafts = data.data();

        String economicDataString = mapper.writeValueAsString(economicDrafts);

        Json jsonData = Json.of(economicDataString);

        return repository.saveEconomicData(
                DataEconomicEntity.builder().data(jsonData).createdAt(Instant.now()).updatedAt(Instant.now()).build());
    }

    public Mono<GetAll<DataEconomicDraft>> getAllinfo(Integer page, Integer limit, Instant createAt) {
        return repository.getAll(page, limit, createAt);
    }
}
