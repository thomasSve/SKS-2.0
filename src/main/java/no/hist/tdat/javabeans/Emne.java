package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by vimCnett
 */
public class Emne {
    private String emneKode;
    private String emneNavn;
    private ArrayList<DelEmne> delemner;
    private int foreleser;

    public Emne(String kode, String navn) {
        this.emneKode = kode;
        this.emneNavn = navn;
    }

    public Emne() {
    }

    public String getEmneKode() {
        return emneKode;
    }

    public void setEmneKode(String emneKode) {
        this.emneKode = emneKode;
    }

    public int getForeleser() {
        return foreleser;
    }

    public void setForeleser(int foreleser) {
        this.foreleser = foreleser;
    }

    public String getEmneNavn() {
        return emneNavn;
    }

    public void setEmneNavn(String emneNavn) {
        this.emneNavn = emneNavn;
    }

    public ArrayList<DelEmne> getDelemner() {
        return delemner;
    }

    public void setDelemner(ArrayList<DelEmne> delemner) {
        this.delemner = delemner;
    }

    public void leggTilDelEmne(DelEmne delEmne){
        delemner.add(delEmne);
        System.out.println();
    }

}
