package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.LineStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LineStationRepository
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/09/29
 */
public interface LineStationRepository extends JpaRepository<LineStation, Long> {
}
