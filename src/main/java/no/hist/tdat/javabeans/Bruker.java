package no.hist.tdat.javabeans;


import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.utils.PassordService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vimCnett on 09.01.14.
 * NB!!! Mangler variabel for øvinger som er gjort
 */
public class Bruker {
    @NotBlank
    private String mail;
    private Integer rettighet;
    private String fornavn;
    private String etternavn;
    @NotBlank
    private String passord;
    private String gammeltPassord;
    private String bekreftPassord;
    private String nyttPassord;

    private int aktiv;
    private ArrayList<Emner> emner;

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn, int aktiv) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = PassordService.genererPassord();
        this.aktiv = aktiv;
        emner = new ArrayList<Emner>();
    }

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = PassordService.genererPassord();
        this.aktiv = 1;
        emner = new ArrayList<Emner>();
    }

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn, String passord) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        setPassord(passord);
        this.aktiv = 1;
        emner = new ArrayList<Emner>();
    }

    public Bruker() {

    }

    /**
     * Konstruktør for innlogging
     *
     * @param mail Brukerens epostadresse
     * @param passord brukerens passord
     */
    public Bruker(String mail, String passord) {
        this.mail = mail;
        this.passord = PassordService.krypterPassord(passord);
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
     *
     * @param passord
     */
    public void setPassord(String passord) {
        this.passord = PassordService.krypterPassord(passord);
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



    public void addEmne() {
        //TODO legg til et emne en bruker er medlem av. Her skal ikke tilgangsrettigheter være
    }



    /**
     * Tar inn tre variabler, det gamle, nye og bekrefta det nye.
     *
     * @param gPassord passord, nytt PW & bekreft nytt PW
     * @return Boolean, passordet endret eller ikkje
     * @author vimCnett
     */
    public boolean endrePassord(String gPassord, String nPassord, String bPassord) {
        if ((gPassord.equals(this.passord)) && (nPassord.equals(bPassord))) {
            setPassord(nPassord);
            return true;
        }
        return false;
    }

    public String getBekreftPassord() {
        return bekreftPassord;
    }

    public void setBekreftPassord(String bekreftPassord) {
        this.bekreftPassord = bekreftPassord;
    }

    public String getGammeltPassord() {
        return gammeltPassord;
    }

    public void setGammeltPassord(String gammeltPassord) {
        this.gammeltPassord = gammeltPassord;
    }

    public String getNyttPassord() {
        return nyttPassord;
    }

    public void setNyttPassord(String nyttPassord) {
        this.nyttPassord = nyttPassord;
    }



}

