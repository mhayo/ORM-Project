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
- **[Jupyter Notebooks](https://jupyter.org/)**: Used for analyzing and visualizing performance data.
- **[Python](https://www.python.org/)**: Used within Jupyter Notebooks for data analysis.
- **[MySQL](https://www.mysql.com/)**: The database used for storing and retrieving data.

## Modules
- **JDBC Module**: Implements the API using pure JDBC for database interactions.
- **JPA Module**: Utilizes Spring Data JPA for implementing persistence logic.
- **MyBatis Module**: Implements persistence logic using the MyBatis framework.
- **Shared Module**: A common module created for performance measurement across all other modules.

## Performance Analysis
Performance metrics are logged and exported as CSV files, which are subsequently analyzed using Jupyter Notebooks with Python to visualize and interpret the data.

## Usage
This project is an experimental application designed for comparing different persistence technologies and is not intended for production use. It deliberately omits authentication mechanisms to focus on core functionality and performance analysis.

## License
This project is open for use by anyone without restrictions.
