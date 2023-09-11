package org.akad.mhayo.orm_project.mybatis_module;

import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MeasurementConfig.class)
public class MybatisModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisModuleApplication.class, args);
    }

}
