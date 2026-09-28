package com.commute.api.domain.station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * 路線と駅の関連エンティティ。
 * Association entity between a line and a station.
 *
 * <p>
 * 路線上の駅順序（sequence）を保持する。
 * Holds the sequence (order) of the station on the line.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "line_stations")
public class LineStation {

    /** 路線駅関連ID / Line-station association ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_station_id", nullable = false)
    private Long id;

    /** 路線 / Line */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "line_id", nullable = false)
    private Line line;

    /** 駅 / Station */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    /** 路線上の駅順序 / Station sequence on the line */
    @NotNull
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    /**
     * 路線駅関連IDを取得する。
     * Gets the line-station association ID.
     *
     * @return 路線駅関連ID / Line-station association ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 路線駅関連IDを設定する。
     * Sets the line-station association ID.
     *
     * @param id 路線駅関連ID / Line-station association ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 路線を取得する。
     * Gets the line.
     *
     * @return 路線 / Line
     */
    public Line getLine() {
        return line;
    }

    /**
     * 路線を設定する。
     * Sets the line.
     *
     * @param line 路線 / Line
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * 駅を取得する。
     * Gets the station.
     *
     * @return 駅 / Station
     */
    public Station getStation() {
        return station;
    }

    /**
     * 駅を設定する。
     * Sets the station.
     *
     * @param station 駅 / Station
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * 路線上の駅順序を取得する。
     * Gets the station sequence on the line.
     *
     * @return 駅順序 / Station sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 路線上の駅順序を設定する。
     * Sets the station sequence on the line.
     *
     * @param sequence 駅順序 / Station sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
