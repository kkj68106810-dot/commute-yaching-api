package com.commute.api.domain.station.service;

import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Line;
import com.commute.api.domain.station.entity.LineStation;
import com.commute.api.domain.station.entity.Prefecture;
import com.commute.api.domain.station.entity.Station;
import com.commute.api.domain.station.repository.LineRepository;
import com.commute.api.domain.station.repository.LineStationRepository;
import com.commute.api.domain.station.repository.PrefectureRepository;
import com.commute.api.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * StationMigrationService
 *
 * @author Kim Gwangjin
 * @version 1.0.0
 * @since 2026/09/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StationMigrationService {

//    private static final Logger log = (Logger) LoggerFactory.getLogger(StationMigrationService.class);

    /** フィールド説明 / Field description */
    private final HeartRailsApiClient apiClient;
    /** フィールド説明 / Field description */
    private final LineRepository lineRepository;
    /** フィールド説明 / Field description */
    private final StationRepository stationRepository;
    /** フィールド説明 / Field description */
    private final PrefectureRepository prefectureRepository;
    /** フィールド説明 / Field description */
    private final LineStationRepository lineStationRepository;

    /**
     * 処理内容を記入する。
     * Write what this method does.
     */
    @Transactional
    public void migrateLineToStation () {
        List<Line> lineList = lineRepository.findAll();
        Map<String, Line> lineMap = lineList.stream()
                .collect(Collectors.toMap(Line::getLineName, line -> line));

        List<HeartRailsStationDto.StationInfo> stationList = new ArrayList<>();

        for(Line line : lineList) {
            stationList.addAll(apiClient.getStations(line.getLineName()));
        }

        Map<String, List<HeartRailsStationDto.StationInfo>> groupedStation = stationList.stream()
                .collect(Collectors.groupingBy(HeartRailsStationDto.StationInfo::name));

        for (Map.Entry<String, List<HeartRailsStationDto.StationInfo>> entry : groupedStation.entrySet()) {
            log.info("Station name: {}", entry.getKey());
            String stationName = entry.getKey();
            List<HeartRailsStationDto.StationInfo> linesForStation = entry.getValue();

            HeartRailsStationDto.StationInfo firstLine = linesForStation.get(0);

            Station station = new Station();
            station.setStationName(stationName);
            station.setLatitude(firstLine.y());
            station.setLatitude(firstLine.x());
            Prefecture prefecture = prefectureRepository.findByPrefName(firstLine.prefecture());
            station.setPrefecture(prefecture);

            Station savedStation = stationRepository.save(station);

            List<LineStation> mapping = linesForStation.stream()
                    .map(info -> {
                        Line matchedLine = lineRepository.findByLineName(info.line());
                        LineStation lineStation = new LineStation();
                        lineStation.setLine(matchedLine);
                        lineStation.setStation(savedStation);
                        return lineStation;
                    }).toList();
            lineStationRepository.saveAll(mapping);
        }
    }



}
