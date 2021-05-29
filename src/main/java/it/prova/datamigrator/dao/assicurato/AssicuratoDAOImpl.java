package it.prova.datamigrator.dao.assicurato;

import it.prova.datamigrator.dao.AbstractMySQLDAO;
import it.prova.datamigrator.model.Assicurato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class AssicuratoDAOImpl extends AbstractMySQLDAO {

    public int insert(Assicurato input) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO assicurato (nome,cognome,data_nascita,codice_fiscale,numero_sinistri)" +
                    " VALUES (?, ?, ?, ?, ?);")) {

            connection.setAutoCommit(false);

            ps.setString(1, input.getNome());
            ps.setString(2, input.getCognome());
            ps.setDate(3, (Date) input.getDataNascita());
            ps.setString(4, input.getCodiceFiscale());
            ps.setInt(5, input.getNumeroSinistri());

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
