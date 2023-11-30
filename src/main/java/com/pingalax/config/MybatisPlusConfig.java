package com.pingalax.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouxiaotao
 * @Description: MybatisPlus配置类
 * @date 2023-09-07 17:07
 */
//@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    //    //当为-1的时候查询全部
    //    paginationInterceptor.setLimit(-1);
    //    return paginationInterceptor;
    //
    //}
    /*
      高版本springboot替代了paginationInterceptor
      @Bean
      public MybatisPlusInterceptor mybatisPlusInterceptor() {
           MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
           interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
           return interceptor;
      }
     */


    /**
     * 注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
