package com.beadando.tarskereso.model;

import java.util.ArrayList;

public class User {

    private Integer id;
    private Nem_enum nem;
    private String nev;
    private Integer kor;
    private String leiras;

    public Nem_enum getNem() {
        return nem;
    }

    public void setNem(Nem_enum nem) {
        this.nem = nem;
    }

    public ArrayList<Integer> likeok;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Integer getKor() {
        return kor;
    }

    public void setKor(Integer kor) {
        this.kor = kor;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public User(Integer id, String nev, Integer kor, String leiras, ArrayList<Integer> likeok) {
        this.id = id;
        this.nev = nev;
        this.kor = kor;
        this.leiras = leiras;
        this.likeok = likeok;
    }
    public User(){}
}