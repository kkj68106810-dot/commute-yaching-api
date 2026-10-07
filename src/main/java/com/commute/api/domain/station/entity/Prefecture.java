package com.commute.api.domain.station.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prefectures")
public class Prefecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefecture_id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "pref_name", nullable = false, length = 50)
    private String prefName;


}