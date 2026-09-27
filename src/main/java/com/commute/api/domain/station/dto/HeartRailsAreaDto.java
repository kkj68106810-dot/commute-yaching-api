package com.commute.api.domain.station.dto;

import java.util.List;

// 1. 구역 (Prefecture) 응답 DTO
public record HeartRailsAreaDto(AreaResponse response) {
    public record AreaResponse(List<String> area) {}
}

