package com.files.persistance.repository;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.files.persistance.repository.entities.DataEconomicEntity;

import reactor.core.publisher.Flux;

public interface IEconomicDataRepository extends ReactiveCrudRepository<DataEconomicEntity, UUID> {

    @Query("""
            SELECT data FROM economic_data
            WHERE (:createAt IS NULL OR created_at >= :createAt)
            ORDER BY created_at DESC
            LIMIT :limit
            OFFSET :offset
            """)
    Flux<String> getAllPagination(Integer offset, Integer limit, Instant createAt);
}
