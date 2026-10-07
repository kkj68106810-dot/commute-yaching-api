package com.commute.api.domain.station.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prefecture_id", nullable = false)
    private Prefecture prefecture;

    @Size(max = 100)
    @NotNull
    @Column(name = "sta_name", nullable = false, length = 100)
    private String staName;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private double longitude;


}