package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 路線エンティティのリポジトリインタフェース。
 * Repository interface for the Line entity.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public interface LineRepository extends JpaRepository<Line, Long> {

}
