package com.cytech.imagesearchwebsiteapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description: 全局跨域设置
 * @title: GlobalCorsConfig
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //是否发送Cookie信息
//        config.setAllowCredentials(true);
        //放行哪些原始域
        // TODO 由于        config.setAllowCredentials(true); 所以不能为*号，注意指定未来的前端
//        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("https://ce-ceone-admin.herokuapp.com");
        config.addAllowedOrigin(CorsConfiguration.ALL);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod(CorsConfiguration.ALL);
        //放行哪些原始域(头部信息)
        config.addAllowedHeader(CorsConfiguration.ALL);
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("Access-Control-Allow-*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/api/**", config);
        configSource.registerCorsConfiguration("/v2/api-docs", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}
