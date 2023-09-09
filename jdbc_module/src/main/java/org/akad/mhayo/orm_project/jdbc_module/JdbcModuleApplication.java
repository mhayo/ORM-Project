package org.akad.mhayo.orm_project.jdbc_module;

import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.datasource.InitDataBase;
import org.akad.mhayo.orm_project.util.exceptions.DataBaseConnectException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class JdbcModuleApplication {

	public static void main(String[] args) {

		try {
			Connection connection = DataBaseConnection.getConnection();
			InitDataBase.dbInit(connection);
		} catch (Exception e) {
			throw new DataBaseConnectException("Error while connecting to database");
		}

		SpringApplication.run(JdbcModuleApplication.class, args);
	}

}
