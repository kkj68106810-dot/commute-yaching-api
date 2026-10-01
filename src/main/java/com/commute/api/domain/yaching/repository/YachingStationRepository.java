package com.commute.api.domain.yaching.repository;

import com.commute.api.domain.yaching.entity.YachingStat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * YachingStationRepository
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/10/01
 */
public interface YachingStationRepository extends JpaRepository<YachingStat,Long> {

}
