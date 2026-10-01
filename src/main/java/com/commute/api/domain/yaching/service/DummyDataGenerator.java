package com.commute.api.domain.yaching.service;

import com.commute.api.domain.yaching.repository.YachingStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private final YachingStationRepository yachingStationRepository;
    
    

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }
}
