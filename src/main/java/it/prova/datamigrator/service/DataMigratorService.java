package it.prova.datamigrator.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import it.prova.datamigrator.connection.MyConnection;
import it.prova.datamigrator.dao.Constants;
import it.prova.datamigrator.dao.assicurato.AssicuratoDAOImpl;
import it.prova.datamigrator.dao.notprocessed.NotProcessedDAOImpl;
import it.prova.datamigrator.model.Assicurato;
import it.prova.datamigrator.model.NotProcessed;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMigratorService {

    private AssicuratoDAOImpl assicuratoDAO = new AssicuratoDAOImpl();
    private NotProcessedDAOImpl notPrecessedDAO = new NotProcessedDAOImpl();

    public void dataMigrator() throws Exception {

        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEW)) {

            assicuratoDAO.setConnection(connection);
            notPrecessedDAO.setConnection(connection);

            List<Assicurato> oldAssicurati = extractFromCSV("C:\\Users\\Alessandro\\Desktop\\filesinput\\assicurato.csv");

            for (Assicurato assicuratoItem : oldAssicurati) {
                if (validate(assicuratoItem)) {
                    assicuratoDAO.insert(assicuratoItem);
                } else {
                    NotProcessed notProcessed = new NotProcessed();
                    notProcessed.setCodiceFiscale(assicuratoItem.getCodiceFiscale());
                    notProcessed.setOldId(assicuratoItem.getId());

                    notPrecessedDAO.insert(notProcessed);
                }

            }

        } catch (Exception e) {

        }

    }

    public boolean validate(Assicurato input) {

        if(input.getNome().isEmpty() || input.getNome().equals("") || NumberUtils.isCreatable(input.getNome()))
            return false;

        if(input.getCognome().isEmpty() || input.getCognome().equals("") || NumberUtils.isCreatable(input.getCognome()))
            return false;

        if(input.getCodiceFiscale().isEmpty() || input.getCodiceFiscale().equals("") || input.getCodiceFiscale().length() < 16)
            return false;

        if(input.getNumeroSinistri() > 10 || input.getNumeroSinistri() == null)
            return false;

        return true;
    }

    public List<Assicurato> extractFromCSV(String pathname) throws IOException, CsvException {

        List<Assicurato> result = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build(); // custom separator

        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(pathname))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()) {

            List<String[]> assicuratiCSV = reader.readAll();

            for (String[] assicuratoItem : assicuratiCSV) {
                Assicurato assicurato = new Assicurato();
                assicurato.setId(Long.parseLong(assicuratoItem[0]));
                assicurato.setNome(assicuratoItem[1]);
                assicurato.setCognome(assicuratoItem[2]);
                assicurato.setDataNascita(Date.valueOf(assicuratoItem[3]));
                assicurato.setCodiceFiscale(assicuratoItem[4]);
                assicurato.setNumeroSinistri(Integer.parseInt(assicuratoItem[5]));

                result.add(assicurato);
            }
        }
        return result;
    }

}
