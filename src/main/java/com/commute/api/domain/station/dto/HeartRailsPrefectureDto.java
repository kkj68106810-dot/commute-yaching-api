package com.commute.api.domain.station.dto;

import java.util.List;

/**
 * HeartRails都道府県情報レスポンスDTO。
 * HeartRails prefecture information response DTO.
 *
 * @param response 都道府県レスポンス本体 / Prefecture response body
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
public record HeartRailsPrefectureDto(PrefectureResponse response) {

    /**
     * 都道府県リストを保持するレスポンスレコード。
     * Response record that holds the prefecture list.
     *
     * @param prefecture 都道府県名リスト / List of prefecture names
     */
    public record PrefectureResponse(List<String> prefecture) {}
}
