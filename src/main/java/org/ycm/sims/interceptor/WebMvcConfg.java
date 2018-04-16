package org.ycm.sims.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create by yangchangmin
 * 2018/4/17 0:27
 */
@Configuration
@Slf4j
public class WebMvcConfg implements WebMvcConfigurer {

    /**
     * 自己定义的拦截器类
     * @return
     */
    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info(">>>>>>>>>>>>>>>拦截器生效");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/*");
    }
}
