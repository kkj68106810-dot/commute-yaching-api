package com.commute.api.domain.station.dto;

import java.util.List;

/**
 * HeartRailsエリア（地方）情報レスポンスDTO。
 * HeartRails area (region) information response DTO.
 *
 * @param response エリアレスポンス本体 / Area response body
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public record HeartRailsAreaDto(AreaResponse response) {

    /**
     * エリアリストを保持するレスポンスレコード。
     * Response record that holds the area list.
     *
     * @param area エリア名リスト / List of area names
     */
    public record AreaResponse(List<String> area) {}
}
