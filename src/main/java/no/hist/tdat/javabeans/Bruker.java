package no.hist.tdat.javabeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vimCnett on 09.01.14.
 */
public class Bruker {
    private static final String RANDOM_TEGN  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    private String mail;
    private Integer rettighet;
    private String fornavn;
    private String etternavn;
    private String passord;
    private int aktiv;
    private ArrayList<Emner> emner;

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn, int aktiv){
        this.mail = mail;
        this.rettighet = rettighet;
        this. fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = genererPassord();
        this.aktiv = aktiv;
        emner = new ArrayList<Emner>();
    }

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn){
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

    public Integer getRettighet() {
        return rettighet;
    }

    public void setRettighet(Integer rettighet) {
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

    /**
     * Bruker hjelpemetoden krypterPassord til å sette passord til bruker
     * @param passord
     */  /**
    public void setPassord(String passord) {
        this.passord = krypterPassord(passord);
    }
            */
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

    /**
     * Hjelpemetode til genererPassord, generer random int verdier
     * @return et tall mellom 0 og RANDOM_TEGN.length()
     */
    public int randomIndex (){
        int min = 0;
        int max = RANDOM_TEGN.length();
        return random.nextInt((max-min)+1)+min;

    }

    /**
     * Generer et random passord på lengde 6 tegn
     * @return kryptert random passord på lengde 6
     */
    public String genererPassord(){
        String passord = "";
        for (int i = 0; i <6 ; i++) {
            passord+=RANDOM_TEGN.charAt(randomIndex());
        }
        return passord;
    }




    public void addEmne(){
        //TODO legg til et emne en bruker er medlem av. Her skal ikke tilgangsrettigheter være
    }

}
