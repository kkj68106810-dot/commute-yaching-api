package com.commute.api.domain.station.dto;

import java.util.List;

// 1. 도도부현 (Prefecture) 응답 DTO
public record HeartRailsPrefectureDto(PrefectureResponse response) {
    public record PrefectureResponse(List<String> prefecture) {}
}

