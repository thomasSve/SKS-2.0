package no.hist.tdat.javabeans;


import no.hist.tdat.javabeans.utils.PassordService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

/**
 * Created by vimCnett on 09.01.14.
 * NB!!! Mangler variabel for øvinger som er gjort
 */
@Scope("session")
public class Bruker {
    public static final int STUDENT_RETTIGHET = 1;
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
     * @param mail    Brukerens epostadresse
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
}

