-- Create the jpa database
CREATE SCHEMA `jpa` ;
CREATE USER 'jpa'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON jpa.* TO 'jpa'@'localhost';

-- create jdbc database
CREATE SCHEMA `jdbc` ;
CREATE USER 'jdbc'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON jdbc.* TO 'jdbc'@'localhost';

-- create mybatis database
CREATE SCHEMA `mybatis` ;
CREATE USER 'mybatis'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON mybatis.* TO 'jpa'@'localhost';
