package com.commute.api.domain.station.controller;

import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Line;
import com.commute.api.domain.station.entity.Prefecture;
import com.commute.api.domain.station.entity.Station;
import com.commute.api.domain.station.repository.LineRepository;
import com.commute.api.domain.station.repository.PrefectureRepository;
import com.commute.api.domain.station.repository.StationRepository;
import com.commute.api.domain.station.service.HeartRailsApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * HeartRails外部APIから駅関連マスタを取得し、DBへ登録するコントローラー。
 * Controller that fetches station-related master data from HeartRails external API and persists it to the DB.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/station")
@CrossOrigin(origins = "http://localhost:3000")
public class OpenAPIController {

    /** HeartRails APIクライアント / HeartRails API client */
    private final HeartRailsApiClient heartRailsApiClient;

    /** 都道府県リポジトリ / Prefecture repository */
    private final PrefectureRepository prefectureRepository;

    /** 路線リポジトリ / Line repository */
    private final LineRepository lineRepository;

    /** 駅リポジトリ / Station repository */
    private final StationRepository stationRepository;

    /**
     * 全国の都道府県一覧をHeartRailsから取得し、DBへ一括登録する。
     * Fetches the nationwide prefecture list from HeartRails and bulk-saves it to the DB.
     */
    @GetMapping("/prefecture/all")
    public void getPrefectures() {
        List<String> prefectureList = heartRailsApiClient.getPrefectures();
        List<Prefecture> prefectures = new ArrayList<>();
        for (String prefectureName : prefectureList) {
            Prefecture prefecture = new Prefecture();
            prefecture.setPrefName(prefectureName);
            prefectures.add(prefecture);
        }
        prefectureRepository.saveAll(prefectures);
    }

    /**
     * DB上の全都道府県について路線一覧を取得し、DBへ一括登録する。
     * For every prefecture in the DB, fetches the line list and bulk-saves it to the DB.
     */
    @GetMapping("/line/all")
    public void getLines() {
        List<Prefecture> prefList = prefectureRepository.findAll();
        List<Line> lines = new ArrayList<>();
        for (Prefecture prefectureName : prefList) {
            List<String> getLines = heartRailsApiClient.getLines(prefectureName.getPrefName());
            for (String lineName : getLines) {
                Line line = new Line();
                line.setLineName(lineName);
                lines.add(line);
            }
        }
        lineRepository.saveAll(lines);
    }

    /**
     * DB上の全路線について駅一覧を取得し、DBへ一括登録する。
     * For every line in the DB, fetches the station list and bulk-saves it to the DB.
     *
     * <p>現在はコメントアウト中。 / Currently commented out.</p>
     */
//    @GetMapping("/station/all")
//    public void getStations() {
//        List<Line> lines = lineRepository.findAll();
//        List<Station> stations = new ArrayList<>();
//        for (Line line : lines) {
//            List<HeartRailsStationDto.StationInfo> stationInfos = heartRailsApiClient.getStations(line.getLineName());
//
//            for (HeartRailsStationDto.StationInfo stationInfo : stationInfos) {
//                Station station = new Station();
//
//                station.setStationName(stationInfo.name());
//                Prefecture pref = prefectureRepository.getByPrefName(stationInfo.prefecture());
//                station.setPrefecture(pref);
//                station.setLatitude(stationInfo.y());
//                station.setLongitude(stationInfo.x());
//                stations.add(station);
//            }
//        }
//        stationRepository.saveAll(stations);
//    }

}
