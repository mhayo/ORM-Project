package org.akad.mhayo.orm_project.jdbc_module;

import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.datasource.InitDataBase;
import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.akad.mhayo.orm_project.util.exceptions.DataBaseConnectException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.sql.Connection;

import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.CONNECTION_ERROR;

@SpringBootApplication
@Import(MeasurementConfig.class)
public class JdbcModuleApplication {

	public static void main(String[] args) {

		try {
			Connection connection = DataBaseConnection.getConnection();
			InitDataBase.dbInit(connection);
		} catch (Exception e) {
			throw new DataBaseConnectException(CONNECTION_ERROR);
		}

		SpringApplication.run(JdbcModuleApplication.class, args);
	}

}
