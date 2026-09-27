package com.commute.api.domain.station.controller;

import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Line;
import com.commute.api.domain.station.entity.Prefecture;
import com.commute.api.domain.station.entity.Station;
import com.commute.api.domain.station.repository.LineRepository;
import com.commute.api.domain.station.repository.PrefectureRepository;
import com.commute.api.domain.station.repository.StationRepository;
import com.commute.api.domain.station.service.HeartRailsApiClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/station")
@CrossOrigin(origins = "http://localhost:3000")
public class OpenAPIController {

    private final HeartRailsApiClient heartRailsApiClient;
    private final PrefectureRepository prefectureRepository;
    private final LineRepository lineRepository;
    private final StationRepository stationRepository;


    public OpenAPIController(HeartRailsApiClient heartRailsApiClient, PrefectureRepository prefectureRepository, LineRepository lineRepository, StationRepository stationRepository) {
        this.heartRailsApiClient = heartRailsApiClient;
        this.prefectureRepository = prefectureRepository;
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

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
