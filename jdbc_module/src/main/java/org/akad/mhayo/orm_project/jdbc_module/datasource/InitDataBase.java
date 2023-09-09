package org.akad.mhayo.orm_project.jdbc_module.datasource;

import org.akad.mhayo.orm_project.jdbc_module.exception.DataBaseInitException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InitDataBase {

    public InitDataBase(){/*standard constructor */}


    public static void dbInit(Connection connection) {

        InputStream file = InitDataBase.class.getResourceAsStream("/schema.sql");

        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        List<String> list = reader.lines().flatMap(String::lines).toList();

        List<String> statements = new ArrayList<>();

        StringBuilder statement = new StringBuilder();

       for(String s: list){

           if(s.contains(";") && (statement.isEmpty())){
               statements.add(s);
           }else {
               if(s.contains(";")){
                   statement.append(s);
                   statements.add(statement.toString());
                   statement = new StringBuilder();
               }else {
                   statement.append(s);
               }
           }

       }

       for (String s: statements){

           try {
               try (PreparedStatement statement1 = connection.prepareStatement(s)) {
                   statement1.execute(s);
               }
           } catch (SQLException e) {
               throw new DataBaseInitException("Error while init database");
           }

       }

    }

}
