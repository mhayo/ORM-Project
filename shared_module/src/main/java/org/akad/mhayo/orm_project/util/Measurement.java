package org.akad.mhayo.orm_project.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public  class Measurement {

    public static void writeToCsv(String framework,String query ,long elapsedTime){

        /*TODO: create file if not exists */

        String path = "../orm_project/src/main/resources/results.csv";

        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter(path,true));
            writer.write(framework + ';' + query + ';' + elapsedTime);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}