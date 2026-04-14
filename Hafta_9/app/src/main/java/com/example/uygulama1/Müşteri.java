package com.example.uygulama1;

public class Müşteri {

    private String adsoyad, telefon, mail;
    private  int id;

    public Müşteri(String adsoyad, String telefon, String mail) {
        this.adsoyad = adsoyad;
        this.telefon = telefon;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
