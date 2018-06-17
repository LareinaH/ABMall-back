package com.cotton.abmallback.web;

import com.cotton.abmallback.web.interceptor.front.CheckLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * FrontConfigurer
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/17
 */
//@Configuration
public class FrontConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(checkLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("un/member/*","/wechat/*");
        super.addInterceptors(registry);
    }
}
