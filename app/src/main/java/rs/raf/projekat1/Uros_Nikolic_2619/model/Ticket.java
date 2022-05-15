package rs.raf.projekat1.Uros_Nikolic_2619.model;

import android.os.Parcel;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int id;
    private String tip;
    private String prioritet;
    private Integer est;
    private String opis;
    private String naslov;
    private int log;

    public Ticket(int id, String tip, String prioritet, Integer est, String naslov, String opis) {
        this.id = id;
        this.tip = tip;
        this.prioritet = prioritet;
        this.est = est;
        this.opis = opis;
        this.naslov = naslov;
        log = 0;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }

    public Integer getEst() {
        return est;
    }

    public void setEst(Integer est) {
        this.est = est;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }
}
