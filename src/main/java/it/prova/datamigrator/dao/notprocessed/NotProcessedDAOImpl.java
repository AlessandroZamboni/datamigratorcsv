package it.prova.datamigrator.dao.notprocessed;

import it.prova.datamigrator.dao.AbstractMySQLDAO;
import it.prova.datamigrator.model.NotProcessed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class NotProcessedDAOImpl extends AbstractMySQLDAO {

    public int insert(NotProcessed input) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO not_processed (codice_fiscale,old_id) VALUES (?, ?);")) {

            connection.setAutoCommit(false);

            ps.setString(1, input.getCodiceFiscale());
            ps.setLong(2, input.getOldId());

            result = ps.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();

            connection.rollback();
            throw e;
        }

        return result;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
