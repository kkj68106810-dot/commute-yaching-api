package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 駅エンティティのリポジトリインタフェース。
 * Repository interface for the Station entity.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public interface StationRepository extends JpaRepository<Station, Long> {

}
