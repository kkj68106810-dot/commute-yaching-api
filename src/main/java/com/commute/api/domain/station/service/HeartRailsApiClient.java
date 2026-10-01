package com.commute.api.domain.station.service;

import com.commute.api.domain.station.dto.HeartRailsLineDto;
import com.commute.api.domain.station.dto.HeartRailsPrefectureDto;
import com.commute.api.domain.station.dto.HeartRailsStationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * HeartRails Express API連携クライアント。
 * Client for HeartRails Express API integration.
 *
 * <p>
 * 都道府県・路線・駅情報を外部APIから取得する。
 * Fetches prefecture, line, and station information from the external API.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Slf4j
@Service
public class HeartRailsApiClient {

    /** RestClientインスタンス / RestClient instance */
    private final RestClient restClient;

    /** HeartRails APIのベースURL / Base URL of HeartRails API */
    private static final String BASE_URL = "https://express.heartrails.com/api/json";

    /**
     * コンストラクタ。RestClientを初期化する。
     * Constructor. Initializes RestClient.
     *
     * @param restClientBuilder RestClientビルダー / RestClient builder
     */
    public HeartRailsApiClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    /**
     * 全国の都道府県一覧を取得する。
     * Retrieves the nationwide list of prefectures.
     *
     * @return 都道府県名のリスト / List of prefecture names
     */
    public List<String> getPrefectures() {
        log.info("[API_REQ] Fetching prefectures from HeartRails API.");
        try {
            HeartRailsPrefectureDto responseDto = restClient.get()
                    .uri("?method=getPrefectures")
                    .retrieve()
                    .body(HeartRailsPrefectureDto.class);

            if (responseDto == null || responseDto.response() == null) {
                log.warn("[API_WARN] Prefecture response body or response object is null.");
                return List.of();
            }

            List<String> prefectures = responseDto.response().prefecture();
            log.info("[API_RES] Successfully fetched {} prefectures.", prefectures != null ? prefectures.size() : 0);
            return prefectures != null ? prefectures : List.of();

        } catch (RestClientException e) {
            log.error("[API_ERR] Failed to fetch prefectures from HeartRails API: {}", e.getMessage(), e);
            throw new RuntimeException("External API error while fetching prefectures", e);
        }
    }

    /**
     * 指定都道府県に属する路線一覧を取得する。
     * Retrieves the list of lines belonging to the specified prefecture.
     *
     * @param prefecture 都道府県名 / Prefecture name
     * @return 路線名のリスト / List of line names
     */
    public List<String> getLines(String prefecture) {
        log.info("[API_REQ] Fetching lines for prefecture: {}", prefecture);
        try {
            HeartRailsLineDto responseDto = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("method", "getLines")
                            .queryParam("prefecture", prefecture)
                            .build())
                    .retrieve()
                    .body(HeartRailsLineDto.class);

            if (responseDto == null || responseDto.response() == null) {
                log.warn("[API_WARN] Line response body or response object is null for prefecture: {}", prefecture);
                return List.of();
            }

            List<String> lines = responseDto.response().line();
            log.info("[API_RES] Successfully fetched {} lines for prefecture: {}", lines != null ? lines.size() : 0, prefecture);
            return lines != null ? lines : List.of();

        } catch (RestClientException e) {
            log.error("[API_ERR] Failed to fetch lines for prefecture '{}': {}", prefecture, e.getMessage(), e);
            throw new RuntimeException("External API error while fetching lines for prefecture: " + prefecture, e);
        }
    }

    /**
     * 指定路線に属する駅一覧（座標含む）を取得する。
     * Retrieves the list of stations (including coordinates) for the specified line.
     *
     * @param lineName 路線名 / Line name
     * @return 駅情報のリスト / List of station information
     */
    public List<HeartRailsStationDto.StationInfo> getStations(String lineName) {
        log.info("[API_REQ] Fetching stations for line: {}", lineName);
        try {
            HeartRailsStationDto responseDto = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("method", "getStations")
                            .queryParam("line", lineName)
                            .build())
                    .retrieve()
                    .body(HeartRailsStationDto.class);

            if (responseDto == null || responseDto.response() == null) {
                log.warn("[API_WARN] Station response body or response object is null for line: {}", lineName);
                return List.of();
            }

            List<HeartRailsStationDto.StationInfo> stations = responseDto.response().station();
            log.info("[API_RES] Successfully fetched {} stations for line: {}", stations != null ? stations.size() : 0, lineName);
            return stations != null ? stations : List.of();

        } catch (RestClientException e) {
            log.error("[API_ERR] Failed to fetch stations for line '{}': {}", lineName, e.getMessage(), e);
            throw new RuntimeException("External API error while fetching stations for line: " + lineName, e);
        }
    }

    /**
     * 路線と駅の関連付けを設定する。
     * Sets the association between lines and stations.
     *
     * <p>実装は今後追加予定。 / Implementation to be added later.</p>
     */
    public void setLineToStation() {
        log.info("[API_INFO] setLineToStation() is not implemented yet.");
    }
}