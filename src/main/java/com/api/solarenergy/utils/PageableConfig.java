package com.api.solarenergy.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PageableConfig {
    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
    return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
}
}
