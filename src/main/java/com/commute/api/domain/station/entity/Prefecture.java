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
 * 都道府県マスタエンティティ。
 * Prefecture master entity.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "prefectures")
public class Prefecture {

    /** 都道府県ID / Prefecture ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefecture_id", nullable = false)
    private Long id;

    /** 都道府県名 / Prefecture name */
    @Size(max = 50)
    @NotNull
    @Column(name = "pref_name", nullable = false, length = 50)
    private String prefName;

    /**
     * 都道府県IDを取得する。
     * Gets the prefecture ID.
     *
     * @return 都道府県ID / Prefecture ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 都道府県IDを設定する。
     * Sets the prefecture ID.
     *
     * @param id 都道府県ID / Prefecture ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 都道府県名を取得する。
     * Gets the prefecture name.
     *
     * @return 都道府県名 / Prefecture name
     */
    public String getPrefName() {
        return prefName;
    }

    /**
     * 都道府県名を設定する。
     * Sets the prefecture name.
     *
     * @param prefName 都道府県名 / Prefecture name
     */
    public void setPrefName(String prefName) {
        this.prefName = prefName;
    }
}
