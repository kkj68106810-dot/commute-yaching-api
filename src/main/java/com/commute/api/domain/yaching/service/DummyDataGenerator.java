package com.commute.api.domain.yaching.service;

import com.commute.api.domain.station.entity.Station;
import com.commute.api.domain.station.repository.StationRepository;
import com.commute.api.domain.yaching.entity.YachingStat;
import com.commute.api.domain.yaching.repository.YachingStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * DummyDataGenerator
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/10/01
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DummyDataGenerator implements CommandLineRunner {

    /** フィールド説明 / Field description */
    private final YachingStationRepository yachingStatRepository;
    /** フィールド説明 / Field description */
    private final StationRepository stationRepository;

    // sinjuku station
    /** フィールド説明 / Field description */
    private static final double CENTER_LAT = 35.6905;
    private static final double CENTER_LNG = 139.6995;

    /**
     * 処理内容を記入する。
     * Write what this method does.
     *
     * @param name 説明 / Description
     * @return 戻り値の説明 / Return value description
     */    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 이미 데이터가 존재하면 실행하지 않음
        if (yachingStatRepository.count() > 0) {
            log.info("야칭 통계 데이터가 이미 존재합니다. 더미 생성을 건너뜁니다.");
            return;
        }

        log.info("5만 건의 야칭 더미 데이터 생성을 시작합니다...");
        List<Station> stations = stationRepository.findAll();
        List<YachingStat> dummyStats = new ArrayList<>();
        String[] roomLayouts = {"1R", "1K", "1DK", "1LDK"};
        Random random = new Random();

        for (Station station : stations) {
            // 1. 도심(신주쿠)과의 거리 계산 (단순 유클리드 거리 또는 하버사인 공식 적용)
            double distance = calculateDistance(CENTER_LAT, CENTER_LNG,
                    station.getLatitude(), station.getLongitude());

            // 2. 방 구조별로 통계 데이터 생성
            for (String layout : roomLayouts) {
                int basePrice = getBasePrice(layout);

                // 3. 거리에 따른 가중치 적용 (가까울수록 비싸고, 멀수록 싸짐)
                // 거리가 1km 멀어질 때마다 가격을 깎는 로직 가정
                int distanceDiscount = (int) (distance * 3000);
                int finalPrice = Math.max(30000, basePrice - distanceDiscount); // 하한선 3만 엔 보장

                // 약간의 무작위성을 부여하여 리얼리티 극대화 (+- 5000엔)
                int randomNoise = (random.nextInt(11) - 5) * 1000;
                int averageYaching = finalPrice + randomNoise;

                YachingStat stat = YachingStat.builder()
                        .station(station)
                        .roomLayout(layout)
                        .averageYaching(averageYaching)
                        .minYaching(averageYaching - 15000)
                        .maxYaching(averageYaching + 20000)
                        .build();

                dummyStats.add(stat);
            }
        }

        // 4. Batch Insert로 대량 적재 최적화
        yachingStatRepository.saveAll(dummyStats);
        log.info("성공적으로 {}건의 야칭 데이터가 적재되었습니다.", dummyStats.size());
    }

    /**
     * 処理内容を記入する。
     * Write what this method does.
     *
     * @param name 説明 / Description
     * @return 戻り値の説明 / Return value description
     */
    private int getBasePrice(String layout) {
        return switch (layout) {
            case "1R" -> 65000;
            case "1K" -> 80000;
            case "1DK" -> 95000;
            case "1LDK" -> 130000;
            default -> 70000;
        };
    }


    /**
     * 処理内容を記入する。
     * Write what this method does.
     *
     * @param name 説明 / Description
     * @return 戻り値の説明 / Return value description
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2))*100;
    };
}
