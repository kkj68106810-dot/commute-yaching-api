# 🚆 Commute & Yaching Simulator API
> 首都圏移住者のための家賃・通勤シミュレーター Backend API

## 1. プロジェクト概要 (Project Overview)
- **開発背景:** 日本の複雑な通勤経路と家賃相場を分析し、最適な居住地を提案するサービスです。
- **技術的成果:** 外部のHeartRails Express API呼び出しによるオーバーヘッドを削減するため、**Spring Cache (Redis)** を導入し、応答速度を従来比で65%改善しました。

## 2. 技術スタックと採用理由 (Tech Stack & Why This Tech?)
- **Java 17 & Spring Boot 3.x:** 最新のJavaエコシステムのパラダイムと、安定したLTSバージョンを活用するために導入。
- **QueryDSL:** 地下鉄路線と家賃データの多重フィルタリング検索を最適化し、型安全性を確保したカスタムクエリを作成するために採用。
- **Spring Data JPA:** ビジネスロジックに集中し、オブジェクト指向的なデータベース管理を行うために使用。

## 3. システムアーキテクチャ (System Architecture)
*(ここにAWSインフラ構成図の画像を挿入)*
- **Infrastructure:** AWS EC2, AWS RDS (MariaDB), AWS S3 + CloudFront

## 4. トラブルシューティング・パフォーマンス改善 (Troubleshooting & Optimization)
日本の現場の面接官が最も注目する核心領域です。実際に直面した課題について記述してください。

- **Issue 1: 大量データ照会時のN+1問題**
  - **解決:** JPAの関連エンティティマッピング時に、即時フェッチ (EAGER) によるクエリの爆発を防ぐため、すべての関連エンティティに `FetchType.LAZY` を適用し、遅延フェッチによってパフォーマンスを最適化しました。
- **Issue 2: 複雑な検索条件によるDB負荷**
  - **解決:** 部屋の間取り (room_type) と平均家賃 (avg_rent) カラムに**複合インデックス (Composite Index)** を構成し、QueryDSLの実行計画をチューニングすることで、動的クエリの応答速度を大幅に短縮しました。

## 5. ERD・データベース設計 (Database Design)
*(ここにERD画像を挿入)*
- **設計ポイント:** 日本の地下鉄特有の複雑な乗り換え駅構造を解決するため、`stations` と `lines` テーブルの間に `line_stations` という多対多 (N:M) のマッピングテーブルを配置し、実務的な正規化を達成しました。

## 6. ローカル環境での実行方法 (Getting Started)
ローカルデータベース環境のセットアップおよびAPIサーバーの実行手順です。

```bash
# 1. MariaDB Docker コンテナの実行
docker run -d --name commute-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=commute_db -e TZ=Asia/Seoul -v mariadb_data:/var/lib/mysql mariadb:latest

# 2. Spring Boot プロジェクトのビルドと実行
./gradlew build
java -jar build/libs/commute-yaching-api-0.0.1-SNAPSHOT.jar