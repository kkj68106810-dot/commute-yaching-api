package com.commute.api.domain.station.dto;

import java.util.List;

// 2. 노선 (Line) 응답 DTO
public record HeartRailsLineDto(LineResponse response) {
    public record LineResponse(List<String> line) {}
}
