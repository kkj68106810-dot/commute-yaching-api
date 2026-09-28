//package com.commute.api.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Swagger / OpenAPIドキュメントの設定クラス。
// * Configuration class for Swagger / OpenAPI documentation.
// *
// * @author Kim Gwangjin
// * @since 2026/09/27
// */
//@Configuration
//public class SwaggerConfig {
//
//    /**
//     * OpenAPI定義Beanを生成する。
//     * Creates the OpenAPI definition bean.
//     *
//     * @return OpenAPI設定オブジェクト / OpenAPI configuration object
//     */
//    @Bean
//    public OpenAPI openAPI() {
//        Info info = new Info()
//                .title("Commute API")
//                .version("1.0")
//                .description("API for Commute");
//        return new OpenAPI()
//                .components(new Components())
//                .info(info);
//    }
//
//}
