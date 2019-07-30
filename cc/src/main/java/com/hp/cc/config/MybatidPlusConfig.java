package com.hp.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/**
 * MybatisPlus 配置 
 * 
 * @author ck
 * @date 2019年6月24日 下午1:36:54
 */

@Configuration
public class MybatidPlusConfig {

	@Bean
    public PerformanceInterceptor performanceInterceptor(){
        //启用性能分析插件
        return new PerformanceInterceptor();
    }
	
	/**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    
    /**
     * mp 逻辑删除配置
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    
}
