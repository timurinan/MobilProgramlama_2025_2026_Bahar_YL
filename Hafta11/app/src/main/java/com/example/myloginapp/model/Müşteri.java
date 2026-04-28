package com.example.myloginapp.model;

import java.io.Serializable;

public class Müşteri implements Serializable {

    private String adsoyad, mail,telefon,anahtar;

    public Müşteri() {
    }

    public Müşteri(String adsoyad, String mail, String telefon) {
        this.telefon = telefon;
        this.mail = mail;
        this.adsoyad = adsoyad;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAnahtar() {
        return anahtar;
    }

    public void setAnahtar(String anahtar) {
        this.anahtar = anahtar;
    }
}
