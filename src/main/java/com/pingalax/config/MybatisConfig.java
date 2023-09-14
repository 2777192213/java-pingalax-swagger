package com.pingalax.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: mybatis配置
 * @Author: chenjh
 * @Date: 2023-02-28
 */
@Configuration
public class MybatisConfig {
    /**
     * Mybatis分页插件
     *
     * @return 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 分页大小为-1时，查询所有数据
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }
}
