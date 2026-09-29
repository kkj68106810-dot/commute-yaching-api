package com.commute.api.domain.station.controller;

import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Line;
import com.commute.api.domain.station.entity.Prefecture;
import com.commute.api.domain.station.entity.Station;
import com.commute.api.domain.station.repository.LineRepository;
import com.commute.api.domain.station.repository.PrefectureRepository;
import com.commute.api.domain.station.repository.StationRepository;
import com.commute.api.domain.station.service.HeartRailsApiClient;
import com.commute.api.domain.station.service.StationMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * HeartRails APIクライアント / HeartRails API client
     */
    private final HeartRailsApiClient heartRailsApiClient;

    /**
     * 都道府県リポジトリ / Prefecture repository
     */
    private final PrefectureRepository prefectureRepository;

    /**
     * 路線リポジトリ / Line repository
     */
    private final LineRepository lineRepository;

    /**
     * 駅リポジトリ / Station repository
     */
    private final StationRepository stationRepository;

    private final StationMigrationService stationMigrationService;

    /**
     * 全国の都道府県一覧をHeartRailsから取得し、DBへ一括登録する。
     * Fetches the nationwide prefecture list from HeartRails and bulk-saves it to the DB.
     */
    @GetMapping("/prefecture/all")
    public void getPrefectures() {
        List<String> prefectureList = heartRailsApiClient.getPrefectures();

        List<Prefecture> prefectures = prefectureList.stream().map(s ->
                {
                    Prefecture pref = new Prefecture();
                    pref.setPrefName(s);
                    return pref;
                })
                .toList();

        prefectureRepository.saveAll(prefectures);
    }

    /**
     * DB上の全都道府県について路線一覧を取得し、DBへ一括登録する。
     * For every prefecture in the DB, fetches the line list and bulk-saves it to the DB.
     */
    @GetMapping("/line/all")
    public void getLines() {
        List<Prefecture> prefList = prefectureRepository.findAll();

        List<Line> lines = prefList.stream()
                .flatMap(pref -> heartRailsApiClient.getLines(pref.getPrefName()).stream())
                .distinct() // String 단계で重複排除（Lineはequals未実装のため entity.distinctは効かない）
                .map(lineName -> {
                    Line newLine = new Line();
                    newLine.setLineName(lineName);
                    return newLine;
                })
                .toList();

        lineRepository.saveAll(lines);
    }

    /**
     * DB上の全路線について駅一覧を取得し、DBへ一括登録する。
     * For every line in the DB, fetches the station list and bulk-saves it to the DB.
     *
     * <p>現在はコメントアウト中。 / Currently commented out.</p>
     */
    @GetMapping("/line/station/all")
    public void getLineToStations() {
//        List<Line> lines = lineRepository.findAll();
//        List<Station> stations = new ArrayList<>();
//
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
        stationMigrationService.migrateLineToStation();
    }

}
