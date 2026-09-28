package com.commute.api.domain.station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 路線マスタエンティティ。
 * Line (railway) master entity.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "line")
public class Line {

    /** 路線ID / Line ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id", nullable = false)
    private Long id;

    /** 路線名 / Line name */
    @Size(max = 100)
    @NotNull
    @Column(name = "line_name", nullable = false, length = 100)
    private String lineName;

    /**
     * 路線IDを取得する。
     * Gets the line ID.
     *
     * @return 路線ID / Line ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 路線IDを設定する。
     * Sets the line ID.
     *
     * @param id 路線ID / Line ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 路線名を取得する。
     * Gets the line name.
     *
     * @return 路線名 / Line name
     */
    public String getLineName() {
        return lineName;
    }

    /**
     * 路線名を設定する。
     * Sets the line name.
     *
     * @param lineName 路線名 / Line name
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

}
