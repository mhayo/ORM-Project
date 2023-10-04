# ORM Project: A Comparative Analysis of Persistence Solutions

## Overview

This project aims to compare various persistence solutions, namely Spring Data JPA, MyBatis, and a pure JDBC connection, by implementing a sample API using each technology. The domain model is inspired by a simple e-commerce application, and the project serves as an experimental application to compare persistence solutions in terms of performance, implementation, and maintenance.

### Key Objectives
- **Performance Analysis**: Evaluate the performance of each persistence solution.
- **Implementation Comparison**: Understand the ease or complexity of implementing each solution.
- **Maintenance Assessment**: Analyze the long-term maintainability and scalability of each solution.

## Technology Stack
- **[Java](https://www.java.com/)**: The primary programming language.
- **[Spring Boot](https://spring.io/projects/spring-boot)**: Used to create stand-alone, production-grade Spring-based applications.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**: Used for implementing JPA-based repositories.
- **[MyBatis](https://mybatis.org/mybatis-3/)**: A SQL Mapping framework.
- **[JDBC](https://docs.oracle.com/javase/tutorial/jdbc/)**: Used to interact with the MySQL database.
- **[Jupyter Notebook](https://jupyter.org/)**: Used for analyzing and visualizing performance data.
- **[Python](https://www.python.org/)**: Used within Jupyter Notebook for data analysis.
- **[MySQL](https://www.mysql.com/)**: The database used for storing and retrieving data.

## Modules
- **JDBC Module**: Implements the API using pure JDBC for database interactions.
- **JPA Module**: Utilizes Spring Data JPA for implementing persistence logic.
- **MyBatis Module**: Implements persistence logic using the MyBatis framework.
- **Shared Module**: A common module created for performance measurement across all other modules.

## Performance Analysis
Performance metrics are logged and exported as CSV files, which are subsequently analyzed using Jupyter Notebooks with Python to visualize and interpret the data.

## Running the Application on Localhost in IDE

### Prerequisites
    - MySQL 8.0.32
    - OpenJDK 17
### Steps
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Launch MySQL Workbench.
4. Create a new MySQL database on localhost:3306.
5. Run dbSchema.sql in the database to create the schema (dbSchema.sql is located in /shared_module/src/main/resources).
6. Start MySQL server.
7. Run the modules: execute the modules from your IDE or via the command line.
8. The modules will run on following ports:
    - JDBC Module: 8083
    - JPA Module: 8081
    - MyBatis Module: 8082
9. OpenAPI doc is available at:
   - JDBC Module: http://localhost:8083/swagger-ui/index.html
   - JPA Module: http://localhost:8081/swagger-ui/index.html
   - MyBatis Module: http://localhost:8082/swagger-ui/index.html
10. Postman Collection is located in the 'postman' directory of the project (ORM Project.postman_collection.json)
11. Performance data is logged in the 'data' directory of the project (data/).
12. Run the Jupyter Notebook in the data directory of the project (notebook.ipynb) to analyze the performance data.

## Usage
This project is an experimental application designed for comparing different persistence technologies and is not intended for production use. It deliberately omits authentication mechanisms to focus on core functionality and performance analysis.

## License
This project is open for use by anyone without restrictions.
