package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {

}
