package com.files.persistance.dtos;

import java.util.List;

import com.files.persistance.models.DataEconomicDraft;

public record SaveEconomicDataRequestDto(List<DataEconomicDraft> data) {

}
