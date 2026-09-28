package com.commute.api.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * ユーザーエンティティ。
 * User entity.
 *
 * <p>
 * 認証情報（メール・パスワードハッシュ）およびロールを保持する。
 * Holds authentication information (email, password hash) and role.
 * </p>
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "uk_users_email",
        columnNames = {"email"})})
public class User {

    /** ユーザーID / User ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    /** メールアドレス / Email address */
    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    /** パスワードハッシュ / Password hash */
    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /** 権限ロール / Authority role */
    @Size(max = 20)
    @NotNull
    @ColumnDefault("'USER'")
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    /** 作成日時 / Created datetime */
    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * ユーザーIDを取得する。
     * Gets the user ID.
     *
     * @return ユーザーID / User ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ユーザーIDを設定する。
     * Sets the user ID.
     *
     * @param id ユーザーID / User ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * メールアドレスを取得する。
     * Gets the email address.
     *
     * @return メールアドレス / Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定する。
     * Sets the email address.
     *
     * @param email メールアドレス / Email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * パスワードハッシュを取得する。
     * Gets the password hash.
     *
     * @return パスワードハッシュ / Password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * パスワードハッシュを設定する。
     * Sets the password hash.
     *
     * @param passwordHash パスワードハッシュ / Password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * 権限ロールを取得する。
     * Gets the authority role.
     *
     * @return 権限ロール / Authority role
     */
    public String getRole() {
        return role;
    }

    /**
     * 権限ロールを設定する。
     * Sets the authority role.
     *
     * @param role 権限ロール / Authority role
     */
    public void setRole(String role) {
        this.role = role;
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
