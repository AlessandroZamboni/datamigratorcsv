package it.prova.datamigrator.service;

import it.prova.datamigrator.connection.MyConnection;
import it.prova.datamigrator.dao.Constants;
import it.prova.datamigrator.dao.assicurato.AssicuratoDAOImpl;
import it.prova.datamigrator.dao.notprocessed.NotProcessedDAOImpl;
import it.prova.datamigrator.dao.oldassicurato.OldAssicuratoDAOImpl;
import it.prova.datamigrator.model.Assicurato;
import it.prova.datamigrator.model.NotProcessed;

import java.sql.Connection;
import java.util.List;

public class DataMigratorService {

    private AssicuratoDAOImpl assicuratoDAO = new AssicuratoDAOImpl();
    private NotProcessedDAOImpl notPrecessedDAO = new NotProcessedDAOImpl();
    private OldAssicuratoDAOImpl oldAssicuratoDAO = new OldAssicuratoDAOImpl();

    public void dataMigrator() throws Exception {

        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEW);
                Connection connection2 = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_OLD)) {

            assicuratoDAO.setConnection(connection);
            notPrecessedDAO.setConnection(connection);
            oldAssicuratoDAO.setConnection(connection2);

            List<Assicurato> oldAssicurati = oldAssicuratoDAO.selectFromOldSchema();

            oldAssicurati.stream().forEach(System.out::println);

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

        if (input.getNumeroSinistri() == null) {
            return false;
        } else if (input.getNome().isEmpty() || input.getNome().equals("")) {
            return false;
        } else if (input.getCognome().isEmpty() || input.getCognome().equals("")) {
            return false;
        } else if (input.getCodiceFiscale().isEmpty() || input.getCodiceFiscale().equals("")) {
            return false;
        }

        return true;

    }

}
