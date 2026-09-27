package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrefectureRepository extends JpaRepository<Prefecture, Long> {

    Prefecture getByPrefName(String prefecture);
}
