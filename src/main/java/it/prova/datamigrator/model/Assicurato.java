package it.prova.datamigrator.model;

import java.util.Date;

public class Assicurato {

    private Long id;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String codiceFiscale;
    private Integer numeroSinistri;

    public Assicurato() {}

    public Assicurato(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale, Integer numeroSinistri) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.numeroSinistri = numeroSinistri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Integer getNumeroSinistri() {
        return numeroSinistri;
    }

    public void setNumeroSinistri(Integer numeroSinistri) {
        this.numeroSinistri = numeroSinistri;
    }

    @Override
    public String toString() {
        return "Assicurato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", numeroSinistri=" + numeroSinistri +
                '}';
    }
}
