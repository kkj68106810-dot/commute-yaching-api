package com.commute.api.domain.station.service;

import com.commute.api.domain.station.dto.HeartRailsStationDto;
import com.commute.api.domain.station.entity.Line;
import com.commute.api.domain.station.repository.LineRepository;
import com.commute.api.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StationMigrationService
 *
 * @author Kim Gwangjin
 * @version 1.0.0
 * @since 2026/09/28
 */
@Service
@RequiredArgsConstructor
public class StationMigrationService {

    /** フィールド説明 / Field description */
    private final HeartRailsApiClient apiClient;
    /** フィールド説明 / Field description */
    private final LineRepository lineRepository;

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

    }



}
