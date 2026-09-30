package com.commute.api.domain.station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 駅マスタエンティティ。
 * Station master entity.
 *
 * <p>
 * 都道府県・駅名・緯度・経度を保持する。
 * Holds prefecture, station name, latitude, and longitude.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "stations", indexes = {@Index(name = "idx_station_pref_name",
        columnList = "prefecture_id, sta_name")})
public class Station {

    /** 駅ID / Station ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private Long id;

    /** 所属都道府県 / Belonging prefecture */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prefecture_id", nullable = false)
    private Prefecture prefecture;

    /** 駅名 / Station name */
    @Size(max = 100)
//    @NotNull
    @Column(name = "sta_name", nullable = false, length = 100)
    private String stationName;

    /** 緯度 / Latitude */
//    @NotNull
    @Column(name = "latitude", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    /** 経度 / Longitude */
//    @NotNull
    @Column(name = "longitude", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    /**
     * 駅IDを取得する。
     * Gets the station ID.
     *
     * @return 駅ID / Station ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 駅IDを設定する。
     * Sets the station ID.
     *
     * @param id 駅ID / Station ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属都道府県を取得する。
     * Gets the belonging prefecture.
     *
     * @return 都道府県 / Prefecture
     */
    public Prefecture getPrefecture() {
        return prefecture;
    }

    /**
     * 所属都道府県を設定する。
     * Sets the belonging prefecture.
     *
     * @param prefecture 都道府県 / Prefecture
     */
    public void setPrefecture(Prefecture prefecture) {
        this.prefecture = prefecture;
    }

    /**
     * 緯度を取得する。
     * Gets the latitude.
     *
     * @return 緯度 / Latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 緯度を設定する。
     * Sets the latitude.
     *
     * @param latitude 緯度 / Latitude
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 経度を取得する。
     * Gets the longitude.
     *
     * @return 経度 / Longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 経度を設定する。
     * Sets the longitude.
     *
     * @param longitude 経度 / Longitude
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 駅名を取得する。
     * Gets the station name.
     *
     * @return 駅名 / Station name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * 駅名を設定する。
     * Sets the station name.
     *
     * @param stationName 駅名 / Station name
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
