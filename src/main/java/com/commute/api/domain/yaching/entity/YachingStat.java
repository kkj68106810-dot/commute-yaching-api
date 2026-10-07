package com.commute.api.domain.yaching.entity;

import com.commute.api.domain.station.entity.Station;
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
import lombok.*;
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
@Getter
@Setter
@Entity
@Table(name = "yaching_stats", indexes = {@Index(name = "idx_yaching_stats_room_avg",
        columnList = "room_layout, average_yaching")})
@NoArgsConstructor
@AllArgsConstructor
public class YachingStat {

    /**
     * 家賃統計ID / Rent statistics ID
     * -- GETTER --
     * 家賃統計IDを取得する。
     * Gets the rent statistics ID.
     *
     * @return 家賃統計ID / Rent statistics ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ya_id", nullable = false)
    private Long id;

    /**
     * 路線駅関連 / Line-station association
     * -- GETTER --
     * 路線駅関連を取得する。
     * Gets the line-station association.
     *
     * @return 路線駅関連 / Line-station association
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    /**
     * 間取り（例: 1K, 1LDK） / Room layout (e.g. 1K, 1LDK)
     * -- GETTER --
     * 間取りを取得する。
     * Gets the room layout.
     *
     * @return 間取り / Room layout
     */
    @Size(max = 10)
    @NotNull
    @Column(name = "room_layout", nullable = false, length = 10)
    private String roomLayout;

    /**
     * 平均家賃 / Average rent
     * -- GETTER --
     * 平均家賃を取得する。
     * Gets the average rent.
     *
     * @return 平均家賃 / Average rent
     */
    @NotNull
    @Column(name = "average_yaching", nullable = false)
    private Integer averageYaching;

    /**
     * 最低家賃 / Minimum rent
     * -- GETTER --
     * 最低家賃を取得する。
     * Gets the minimum rent.
     *
     * @return 最低家賃 / Minimum rent
     */
    @Column(name = "min_yaching")
    private Integer minYaching;

    /**
     * 最高家賃 / Maximum rent
     * -- GETTER --
     * 最高家賃を取得する。
     * Gets the maximum rent.
     *
     * @return 最高家賃 / Maximum rent
     */
    @Column(name = "max_yaching")
    private Integer maxYaching;

    /**
     * 更新日時 / Updated datetime
     * -- GETTER --
     * 更新日時を取得する。
     * Gets the updated datetime.
     *
     * @return 更新日時 / Updated datetime
     */
    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Builder
    public YachingStat(Long id, Integer minYaching, Integer maxYaching, Instant updatedAt, Station station, String roomLayout, Integer averageYaching) {
        this.id = id;
        this.minYaching = minYaching;
        this.maxYaching = maxYaching;
        this.updatedAt = updatedAt;
        this.station = station;
        this.roomLayout = roomLayout;
        this.averageYaching = averageYaching;
    }

}
