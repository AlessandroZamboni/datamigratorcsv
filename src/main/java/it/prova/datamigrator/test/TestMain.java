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

        //################# CSV READER TEST ##############
     /*     String fileName = "C:\\Users\\Alessandro\\Desktop\\filesinput\\assicurato.csv";
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build(); // custom separator
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build())
        {
            System.out.println("########## ESTRAZIONE #########\n");

            List<String[]> r = reader.readAll();
          //  r.forEach(x -> System.out.println(Arrays.toString(x)));
            r.stream().flatMap(Arrays::stream).forEach(System.out::println);


            System.out.println("\n########## FINE ESTRAZIONE #########\n");

            for(String[] assicuratoItem : r) {


                Assicurato assicurato = new Assicurato();
                assicurato.setId(Long.parseLong(assicuratoItem[0]));
                assicurato.setNome(assicuratoItem[1]);
                assicurato.setCognome(assicuratoItem[2]);
                assicurato.setDataNascita(Date.valueOf(assicuratoItem[3]));
                assicurato.setCodiceFiscale(assicuratoItem[4]);
                assicurato.setNumeroSinistri(Integer.parseInt(assicuratoItem[5]));

                System.out.println("ASSICURATO COSTRUITO: "+assicurato);

            }*/

        }

}
