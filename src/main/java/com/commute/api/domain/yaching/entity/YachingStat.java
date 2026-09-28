package com.commute.api.domain.yaching.entity;

import com.commute.api.domain.station.entity.LineStation;
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
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * 家賃（ヤチン）統計エンティティ。
 * Rent (yaching) statistics entity.
 *
 * <p>
 * 路線駅ごとの間取り別家賃統計（平均・最小・最大）を保持する。
 * Holds rent statistics (average, min, max) by room layout for each line-station.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "yaching_stats", indexes = {@Index(name = "idx_yaching_stats_room_avg",
        columnList = "room_layout, average_yaching")})
public class YachingStat {

    /** 家賃統計ID / Rent statistics ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ya_id", nullable = false)
    private Long id;

    /** 路線駅関連 / Line-station association */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "line_station_id", nullable = false)
    private LineStation lineStation;

    /** 間取り（例: 1K, 1LDK） / Room layout (e.g. 1K, 1LDK) */
    @Size(max = 10)
    @NotNull
    @Column(name = "room_layout", nullable = false, length = 10)
    private String roomLayout;

    /** 平均家賃 / Average rent */
    @NotNull
    @Column(name = "average_yaching", nullable = false)
    private Integer averageYaching;

    /** 最低家賃 / Minimum rent */
    @Column(name = "min_yaching")
    private Integer minYaching;

    /** 最高家賃 / Maximum rent */
    @Column(name = "max_yaching")
    private Integer maxYaching;

    /** 更新日時 / Updated datetime */
    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * 家賃統計IDを取得する。
     * Gets the rent statistics ID.
     *
     * @return 家賃統計ID / Rent statistics ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 家賃統計IDを設定する。
     * Sets the rent statistics ID.
     *
     * @param id 家賃統計ID / Rent statistics ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 路線駅関連を取得する。
     * Gets the line-station association.
     *
     * @return 路線駅関連 / Line-station association
     */
    public LineStation getLineStation() {
        return lineStation;
    }

    /**
     * 路線駅関連を設定する。
     * Sets the line-station association.
     *
     * @param lineStation 路線駅関連 / Line-station association
     */
    public void setLineStation(LineStation lineStation) {
        this.lineStation = lineStation;
    }

    /**
     * 間取りを取得する。
     * Gets the room layout.
     *
     * @return 間取り / Room layout
     */
    public String getRoomLayout() {
        return roomLayout;
    }

    /**
     * 間取りを設定する。
     * Sets the room layout.
     *
     * @param roomLayout 間取り / Room layout
     */
    public void setRoomLayout(String roomLayout) {
        this.roomLayout = roomLayout;
    }

    /**
     * 平均家賃を取得する。
     * Gets the average rent.
     *
     * @return 平均家賃 / Average rent
     */
    public Integer getAverageYaching() {
        return averageYaching;
    }

    /**
     * 平均家賃を設定する。
     * Sets the average rent.
     *
     * @param averageYaching 平均家賃 / Average rent
     */
    public void setAverageYaching(Integer averageYaching) {
        this.averageYaching = averageYaching;
    }

    /**
     * 最低家賃を取得する。
     * Gets the minimum rent.
     *
     * @return 最低家賃 / Minimum rent
     */
    public Integer getMinYaching() {
        return minYaching;
    }

    /**
     * 最低家賃を設定する。
     * Sets the minimum rent.
     *
     * @param minYaching 最低家賃 / Minimum rent
     */
    public void setMinYaching(Integer minYaching) {
        this.minYaching = minYaching;
    }

    /**
     * 最高家賃を取得する。
     * Gets the maximum rent.
     *
     * @return 最高家賃 / Maximum rent
     */
    public Integer getMaxYaching() {
        return maxYaching;
    }

    /**
     * 最高家賃を設定する。
     * Sets the maximum rent.
     *
     * @param maxYaching 最高家賃 / Maximum rent
     */
    public void setMaxYaching(Integer maxYaching) {
        this.maxYaching = maxYaching;
    }

    /**
     * 更新日時を取得する。
     * Gets the updated datetime.
     *
     * @return 更新日時 / Updated datetime
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 更新日時を設定する。
     * Sets the updated datetime.
     *
     * @param updatedAt 更新日時 / Updated datetime
     */
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
