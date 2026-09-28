package com.commute.api.domain.station.repository;

import com.commute.api.domain.station.entity.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 都道府県エンティティのリポジトリインタフェース。
 * Repository interface for the Prefecture entity.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public interface PrefectureRepository extends JpaRepository<Prefecture, Long> {

    /**
     * 都道府県名で都道府県エンティティを取得する。
     * Retrieves a prefecture entity by prefecture name.
     *
     * @param prefecture 都道府県名 / Prefecture name
     * @return 該当する都道府県エンティティ / Matching prefecture entity
     */
    Prefecture getByPrefName(String prefecture);
}
