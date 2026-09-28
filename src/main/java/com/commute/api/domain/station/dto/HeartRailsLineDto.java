package com.commute.api.domain.station.dto;

import java.util.List;

/**
 * HeartRails路線情報レスポンスDTO。
 * HeartRails line information response DTO.
 *
 * @param response 路線レスポンス本体 / Line response body
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public record HeartRailsLineDto(LineResponse response) {

    /**
     * 路線リストを保持するレスポンスレコード。
     * Response record that holds the line list.
     *
     * @param line 路線名リスト / List of line names
     */
    public record LineResponse(List<String> line) {}
}
