package com.digitalojt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.digitalojt.web.filter.LogSettingFilter;


@Configuration
public class LogConfig {

    @Bean
    LogSettingFilter logSettingFilterFilter() {

        LogSettingFilter filter = new LogSettingFilter();
        return filter;
    }

}