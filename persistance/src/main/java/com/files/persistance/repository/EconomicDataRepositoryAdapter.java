package com.files.persistance.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.files.persistance.models.DataEconomicDraft;
import com.files.persistance.repository.entities.DataEconomicEntity;
import com.files.persistance.shared.GetAll;
import com.files.persistance.shared.PaginationHelper;

import reactor.core.publisher.Mono;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class EconomicDataRepositoryAdapter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final IEconomicDataRepository repository;

    public EconomicDataRepositoryAdapter(IEconomicDataRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> saveEconomicData(DataEconomicEntity entity) {
        return repository.save(entity).then();
    }

    public Mono<GetAll<DataEconomicDraft>> getAll(Integer page, Integer limit, Instant createAt) {
        int currentPage = page != null ? page : 0;
        int pageSize = limit != null ? limit : 20;

        int offset = currentPage * pageSize;

        return repository.getAllPagination(offset, pageSize, createAt)
                .map(json -> {
                    try {
                        return objectMapper.readValue(
                                json,
                                new TypeReference<List<DataEconomicDraft>>() {
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collectList()
                .map(data -> {

                    List<DataEconomicDraft> result = data.stream()
                            .flatMap(List::stream)
                            .toList();

                    return new GetAll<>(
                            result,
                            new PaginationHelper(
                                    currentPage,
                                    result.size(),
                                    result.size() == pageSize));
                });
    }
}
