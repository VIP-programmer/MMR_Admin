package com.example.mmradmin;

public class Medcin {
    private String cin;
    private String serie;
    private String nom;
    private String prenom;

    public Medcin(String cin, String serie, String nom, String prenom) {
        this.cin = cin;
        this.serie = serie;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }


    public String getCin() {
        return cin;
    }
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
