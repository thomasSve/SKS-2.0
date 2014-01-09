package no.hist.tdat.javabeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyvang on 09.01.14.
 */
public class Bruker {

    private String mail;
    private String rettighet;
    private String fornavn;
    private String etternavn;
    private String passord;
    private int aktiv;
    private ArrayList<Emner> emner;

    public Bruker(String mail, String rettighet, String fornavn, String etternavn){
        this.mail = mail;
        this.rettighet = rettighet;
        this. fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = genererPassord();
        this.aktiv = 1;
        emner = new ArrayList<Emner>();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRettighet() {
        return rettighet;
    }

    public void setRettighet(String rettighet) {
        this.rettighet = rettighet;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public int getAktiv() {
        return aktiv;
    }

    public void setAktiv(int aktiv) {
        this.aktiv = aktiv;
    }

    public ArrayList<Emner> getEmner() {
        return emner;
    }

    public void setEmner(ArrayList<Emner> emner) {
        this.emner = emner;
    }

    public String genererPassord(){
        return "hello,world"; //todo legg til random kode
    }

    /**
     * Legger til emne(String verdi) en bruker er medlem av, denne listen inneholder ikke brukerrettigheter innen et emne eller emne objekt.
     * Det er gitt at emnekode og emnenavn finnes fra før i databasen.
     * @param emnekode
     * @param emnenavn
     */
    public void addEmne(String emnekode, String emnenavn){
        //emner.add(emnekode+", "+ emnenavn);
    }

}
