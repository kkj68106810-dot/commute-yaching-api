package com.commute.api.domain.station.service;

import com.commute.api.domain.station.dto.HeartRailsLineDto;
import com.commute.api.domain.station.dto.HeartRailsPrefectureDto;
import com.commute.api.domain.station.dto.HeartRailsStationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class HeartRailsApiClient {

    private final RestClient restClient;
    private static final String BASE_URL= "https://express.heartrails.com/api/json";


    public HeartRailsApiClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    // 1. 전국 도도부현 목록 조회
    public List<String> getPrefectures() {
        HeartRailsPrefectureDto responseDto = restClient.get()
                .uri("?method=getPrefectures")
                .retrieve()
                .body(HeartRailsPrefectureDto.class);

        return responseDto != null ? responseDto.response().prefecture() : List.of();
    }


    // 2. 특정 도도부현의 노선 목록 조회
    public List<String> getLines(String prefecture) {
        HeartRailsLineDto responseDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("method", "getLines")
                        .queryParam("prefecture", prefecture)
                        .build())
                .retrieve()
                .body(HeartRailsLineDto.class);

        return responseDto != null ? responseDto.response().line() : List.of();
    }

    // 3. 특정 노선의 역 목록 조회 (좌표 포함)
    public List<HeartRailsStationDto.StationInfo> getStations(String lineName) {
        HeartRailsStationDto responseDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("method", "getStations")
                        .queryParam("line", lineName)
                        .build())
                .retrieve()
                .body(HeartRailsStationDto.class);

        return responseDto != null ? responseDto.response().station() : List.of();
    }

}
