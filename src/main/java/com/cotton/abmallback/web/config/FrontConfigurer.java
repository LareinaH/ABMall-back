package com.cotton.abmallback.web.config;

import com.cotton.abmallback.web.interceptor.front.CheckLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * FrontConfigurer
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/17
 */
@Configuration
public class FrontConfigurer  extends WebConfigurer {

    @Bean
    CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(checkLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/un/member/*","/wechat/*","/admin/**","/interface/**");
        super.addInterceptors(registry);
    }
}
