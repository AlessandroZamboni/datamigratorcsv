package it.prova.datamigrator.test;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import it.prova.datamigrator.model.Assicurato;
import it.prova.datamigrator.service.DataMigratorService;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestMain {

    public static void main(String[] args) throws IOException, CsvException {
      DataMigratorService dataMigratorServiceInstance = new DataMigratorService();

        try {
            dataMigratorServiceInstance.dataMigrator();
        } catch (Exception e) {
            e.printStackTrace();
        }



     }

}
