package com.commute.api.domain.user.entity;

import com.commute.api.domain.yaching.entity.YachingStat;
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
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * ユーザーのお気に入り（ブック）エンティティ。
 * User bookmark (favorites) entity.
 *
 * <p>
 * ユーザーがブックマークした家賃統計情報との関連を保持する。
 * Holds the association between a user and bookmarked rent statistics.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "user_book")
public class UserBook {

    /** ブックID / Bookmark ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    /** ユーザー / User */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** ブックマーク対象の家賃統計 / Bookmarked rent statistics */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private YachingStat station;

    /** 作成日時 / Created datetime */
    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * ブックIDを取得する。
     * Gets the bookmark ID.
     *
     * @return ブックID / Bookmark ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ブックIDを設定する。
     * Sets the bookmark ID.
     *
     * @param id ブックID / Bookmark ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ユーザーを取得する。
     * Gets the user.
     *
     * @return ユーザー / User
     */
    public User getUser() {
        return user;
    }

    /**
     * ユーザーを設定する。
     * Sets the user.
     *
     * @param user ユーザー / User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * ブックマーク対象の家賃統計を取得する。
     * Gets the bookmarked rent statistics.
     *
     * @return 家賃統計 / Rent statistics
     */
    public YachingStat getStation() {
        return station;
    }

    /**
     * ブックマーク対象の家賃統計を設定する。
     * Sets the bookmarked rent statistics.
     *
     * @param station 家賃統計 / Rent statistics
     */
    public void setStation(YachingStat station) {
        this.station = station;
    }

    /**
     * 作成日時を取得する。
     * Gets the created datetime.
     *
     * @return 作成日時 / Created datetime
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * 作成日時を設定する。
     * Sets the created datetime.
     *
     * @param createdAt 作成日時 / Created datetime
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
