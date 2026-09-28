package com.commute.api.domain.station.service;

import com.commute.api.domain.station.dto.HeartRailsLineDto;
import com.commute.api.domain.station.dto.HeartRailsPrefectureDto;
import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Prefecture;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
        HeartRailsPrefectureDto responseDto = restClient.get()
                .uri("?method=getPrefectures")
                .retrieve()
                .body(HeartRailsPrefectureDto.class);
        // 3項演算子は使用しない方針 / Policy: avoid ternary operator (kept for null-safety)
        return responseDto != null ? responseDto.response().prefecture() : List.of();
    }

    /**
     * 指定都道府県に属する路線一覧を取得する。
     * Retrieves the list of lines belonging to the specified prefecture.
     *
     * @param prefecture 都道府県名 / Prefecture name
     * @return 路線名のリスト / List of line names
     */
    public List<String> getLines(String prefecture) {
        HeartRailsLineDto responseDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("method", "getLines")
                        .queryParam("prefecture", prefecture)
                        .build())
                .retrieve()
                .body(HeartRailsLineDto.class);
        // 3項演算子は使用しない方針 / Policy: avoid ternary operator (kept for null-safety)
        return responseDto != null ? responseDto.response().line() : List.of();
    }

    /**
     * 指定路線に属する駅一覧（座標含む）を取得する。
     * Retrieves the list of stations (including coordinates) for the specified line.
     *
     * @param lineName 路線名 / Line name
     * @return 駅情報のリスト / List of station information
     */
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

    /**
     * 路線と駅の関連付けを設定する。
     * Sets the association between lines and stations.
     *
     * <p>実装は今後追加予定。 / Implementation to be added later.</p>
     */
    public void setLineToStation() {
    }

}
