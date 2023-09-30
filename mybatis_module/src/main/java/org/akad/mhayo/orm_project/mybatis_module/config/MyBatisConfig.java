package org.akad.mhayo.orm_project.mybatis_module.config;


import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {


    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            // Enable lazy loading
            configuration.setLazyLoadingEnabled(true);

        };
    }


}
