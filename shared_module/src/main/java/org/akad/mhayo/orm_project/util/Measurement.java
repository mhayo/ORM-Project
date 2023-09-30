package org.akad.mhayo.orm_project.util;

import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.akad.mhayo.orm_project.util.exceptions.FileException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.FILE_ERROR;

public class Measurement {

   static Logger logger = Logger.getLogger(Measurement.class.getName());



    public static void writeToCsv(String framework,String query ,long elapsedTime){


        String path = MeasurementConfig.getCsvDir() + MeasurementConfig.getCsvFile();

        logger.info("Writing to csv file");
        logger.info("Path: " + path);

        try {

            Files.createDirectories(Paths.get(MeasurementConfig.getCsvDir()));

            if (!Files.exists(Paths.get(path))) {
                Files.createFile(Paths.get(path));
            }

            BufferedWriter writer = new BufferedWriter( new FileWriter(path,true));
            writer.write(framework + ';' + query + ';' + elapsedTime);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new FileException(FILE_ERROR);
        }
    }
}