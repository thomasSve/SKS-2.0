package no.hist.tdat.javabeans;


import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.utils.PassordService;
import org.hibernate.validator.constraints.NotBlank;
import org.omg.DynamicAny._DynAnyFactoryStub;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vimCnett on 09.01.14.
 * NB!!! Mangler variabel for øvinger som er gjort
 */
@Scope("session")
public class Bruker {
    @NotBlank
    @Email
    private String mail;
    private Integer rettighet;
    private String fornavn;
    private String etternavn;
    private String passord;
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
        this.mail = mail.toLowerCase();
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
        if(passord==null || passord.equals("")){
            this.passord= PassordService.krypterPassord(PassordService.genererPassord());
        }else{
            this.passord = PassordService.krypterPassord(passord);
        }
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
}

