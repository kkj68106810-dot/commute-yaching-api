package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

}
