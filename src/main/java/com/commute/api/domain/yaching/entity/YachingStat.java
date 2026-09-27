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

@Entity
@Table(name = "yaching_stats", indexes = {@Index(name = "idx_yaching_stats_room_avg",
        columnList = "room_layout, average_yaching")})
public class YachingStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ya_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "line_station_id", nullable = false)
    private LineStation lineStation;

    @Size(max = 10)
    @NotNull
    @Column(name = "room_layout", nullable = false, length = 10)
    private String roomLayout;

    @NotNull
    @Column(name = "average_yaching", nullable = false)
    private Integer averageYaching;

    @Column(name = "min_yaching")
    private Integer minYaching;

    @Column(name = "max_yaching")
    private Integer maxYaching;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LineStation getLineStation() {
        return lineStation;
    }

    public void setLineStation(LineStation lineStation) {
        this.lineStation = lineStation;
    }

    public String getRoomLayout() {
        return roomLayout;
    }

    public void setRoomLayout(String roomLayout) {
        this.roomLayout = roomLayout;
    }

    public Integer getAverageYaching() {
        return averageYaching;
    }

    public void setAverageYaching(Integer averageYaching) {
        this.averageYaching = averageYaching;
    }

    public Integer getMinYaching() {
        return minYaching;
    }

    public void setMinYaching(Integer minYaching) {
        this.minYaching = minYaching;
    }

    public Integer getMaxYaching() {
        return maxYaching;
    }

    public void setMaxYaching(Integer maxYaching) {
        this.maxYaching = maxYaching;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}