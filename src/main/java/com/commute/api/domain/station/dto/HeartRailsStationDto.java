package com.commute.api.domain.station.dto;

import java.math.BigDecimal;
import java.util.List;

// 3. 역 (Station) 응답 DTO
public record HeartRailsStationDto(StationResponse response) {
    public record StationResponse(List<StationInfo> station) {}
    
    public record StationInfo(
            String name,        // 역명
            String prefecture,  // 소속 도도부현명
            String line,        // 소속 노선명
            BigDecimal x,           // 경도 (Longitude)
            BigDecimal y            // 위도 (Latitude)
    ) {}
}
