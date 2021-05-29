package it.prova.datamigrator.test;

import it.prova.datamigrator.service.DataMigratorService;

public class TestMain {

    public static void main(String[] args) {
        DataMigratorService dataMigratorServiceInstance = new DataMigratorService();

        try {
            dataMigratorServiceInstance.dataMigrator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
