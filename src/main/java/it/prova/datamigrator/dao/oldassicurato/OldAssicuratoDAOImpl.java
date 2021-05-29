package it.prova.datamigrator.dao.oldassicurato;

import it.prova.datamigrator.dao.AbstractMySQLDAO;
import it.prova.datamigrator.model.Assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OldAssicuratoDAOImpl extends AbstractMySQLDAO {

    public  List<Assicurato> selectFromOldSchema() throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        List<Assicurato> assicurati = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT D.ID, D.CODICE_FISCALE, A.NOME, A.COGNOME, A.DATA_NASCITA, COUNT(S.ID) AS NUMERO_SINISTRI\n" +
                    "FROM dati_fiscali D\n" +
                    "INNER JOIN anagrafica A ON A.FK_DATI_FISCALI = D.ID\n" +
                    "LEFT JOIN sinistri S ON D.ID = S.FK_ANAGRAFICA_ID\n" +
                    "GROUP BY D.ID")) {

            connection.setAutoCommit(false);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Assicurato temp = new Assicurato();
                temp.setId(rs.getLong("ID"));
                temp.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
                temp.setNome(rs.getString("NOME"));
                temp.setCognome(rs.getString("COGNOME"));
                temp.setDataNascita(rs.getDate("DATA_NASCITA"));
                temp.setNumeroSinistri(rs.getInt("NUMERO_SINISTRI"));

                assicurati.add(temp);
            }

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();

            connection.rollback();
            throw e;
        }

        return assicurati;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
