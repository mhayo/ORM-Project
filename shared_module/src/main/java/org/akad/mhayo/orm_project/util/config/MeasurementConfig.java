package org.akad.mhayo.orm_project.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MeasurementConfig {

   // private static String csvPath;

    private static String csvDir;

    @Value("${measurement.directory}")
    public void setCsvDir(String dir) {
        MeasurementConfig.csvDir = dir;
    }
    public static String getCsvDir() {
        return csvDir;
    }

    private static String csvFile;

    @Value("${measurement.file}")
    public void setCsvFile(String path) {
        MeasurementConfig.csvFile = path;
    }
    public static String getCsvFile() {
        return csvFile;
    }



/*
    @Value("${measurement.csv.path}")
    public void setCsvPath(String path) {
        MeasurementConfig.csvPath = path;
    }

    public static String getCsvPath() {
        return csvPath;
    }*/
}
