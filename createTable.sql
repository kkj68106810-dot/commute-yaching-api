-- 1. 기존 테이블 삭제 (의존성 역순)
DROP TABLE IF EXISTS user_book;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS yaching_stats;
DROP TABLE IF EXISTS line_stations;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS line;
DROP TABLE IF EXISTS prefectures;

-- 2. 테이블 생성

-- [1] 都道府県 (prefectures)
CREATE TABLE prefectures
(
    prefecture_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '自治体ID',
    pref_name     VARCHAR(50) NOT NULL COMMENT '都道府県名',
    PRIMARY KEY (prefecture_id)
) COMMENT='都道府県';


-- [2] 路線 (lines)
CREATE TABLE line
(
    line_id   BIGINT AUTO_INCREMENT NOT NULL COMMENT '路線ID',
    line_name VARCHAR(100) NOT NULL COMMENT '路線名',
    PRIMARY KEY (line_id)
) COMMENT='路線';


-- [3] 駅 (stations)
CREATE TABLE stations
(
    station_id    BIGINT AUTO_INCREMENT NOT NULL COMMENT '駅ID',
    prefecture_id BIGINT       NOT NULL COMMENT '自治体ID',
    station_name      VARCHAR(100) NOT NULL COMMENT '駅名',
    latitude DOUBLE NOT NULL COMMENT '緯度 (y)',
    longitude DOUBLE NOT NULL COMMENT '経度 (x)',
    PRIMARY KEY (station_id),
    CONSTRAINT fk_stations_prefectures FOREIGN KEY (prefecture_id) REFERENCES prefectures (prefecture_id)
) COMMENT='駅';


-- [4] 路線_駅マッピング (line_stations)
CREATE TABLE line_stations
(
    line_station_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '路線_駅ID',
    line_id         BIGINT NOT NULL COMMENT '路線ID',
    station_id      BIGINT NOT NULL COMMENT '駅ID',
    sequence        INT    NOT NULL COMMENT '並び順',
    PRIMARY KEY (line_station_id),
    CONSTRAINT fk_line_stations_lines FOREIGN KEY (line_id) REFERENCES line (line_id),
    CONSTRAINT fk_line_stations_stations FOREIGN KEY (station_id) REFERENCES stations (station_id)
) COMMENT='路線_駅マッピング';

-- [5] 駅別家賃 (yaching_stats)
CREATE TABLE yaching_stats
(
    ya_id           BIGINT AUTO_INCREMENT NOT NULL COMMENT '統計ID',
    line_station_id BIGINT      NOT NULL COMMENT '路線駅ID (line_stations.line_station_id)',
    room_layout     VARCHAR(10) NOT NULL COMMENT '間取り',
    average_yaching INT         NOT NULL COMMENT '平均家賃',
    min_yaching     INT                  DEFAULT NULL COMMENT '最小家賃',
    max_yaching     INT                  DEFAULT NULL COMMENT '最大家賃',
    updated_at      TIMESTAMP            DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (ya_id),
    CONSTRAINT fk_yaching_stats_line_stations FOREIGN KEY (line_station_id) REFERENCES line_stations (line_station_id)
) COMMENT='駅別家賃';

-- yaching_stats 테이블의 복합 인덱스 (room_layout, average_yaching)
CREATE INDEX idx_yaching_stats_room_avg ON yaching_stats (room_layout, average_yaching);

-- [6] 会員 (users)
CREATE TABLE users
(
    user_id       BIGINT AUTO_INCREMENT NOT NULL COMMENT '会員ID',
    email         VARCHAR(100) NOT NULL COMMENT 'メールアドレス',
    password_hash VARCHAR(255) NOT NULL COMMENT 'パスワード (BCrypt暗号化)',
    role          VARCHAR(20)  NOT NULL DEFAULT 'USER' COMMENT '権限',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_users_email (email)
) COMMENT='会員';

-- [7] お気に入り駅 (user_book)
CREATE TABLE user_book
(
    book_id    BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ブックマークID',
    user_id    BIGINT    NOT NULL COMMENT '会員ID',
    line_station_id BIGINT    NOT NULL COMMENT '統計ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    PRIMARY KEY (book_id),
    CONSTRAINT fk_user_book_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_book_yaching_stats FOREIGN KEY (line_station_id) REFERENCES yaching_stats (ya_id)
) COMMENT='お気に入り駅';