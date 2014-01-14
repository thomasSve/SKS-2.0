package no.hist.tdat.javabeans;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vimCnett on 09.01.14.
 * NB!!! Mangler variabel for øvinger som er gjort
 */

public class Bruker {
    public static final int STUDENT_RETTIGHET = 1;
    private static final String RANDOM_TEGN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
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
        this.passord = genererPassord();
        this.aktiv = aktiv;
        emner = new ArrayList<Emner>();
    }

    public Bruker(String mail, Integer rettighet, String fornavn, String etternavn) {
        this.mail = mail;
        this.rettighet = rettighet;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = genererPassord();
        this.aktiv = 1;
        emner = new ArrayList<Emner>();
    }

    public Bruker() {
    }

    /**
     * Konstruktør for innlogging
     *
     * @param mail
     * @param passord
     */
    public Bruker(String mail, String passord) {
        this.mail = mail;
        this.passord = krypterPassord(passord);
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
        this.passord = krypterPassord(passord);
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

    /**
     * Hjelpemetode til genererPassord, generer random int verdier
     *
     * @return et tall mellom 0 og RANDOM_TEGN.length()
     */
    public int randomIndex() {
        int min = 0;
        int max = RANDOM_TEGN.length();
        return random.nextInt((max - min) + 1) + min;

    }

    /**
     * Generer et random passord på lengde 6 tegn
     *
     * @return kryptert random passord på lengde 6
     */
    public String genererPassord() {
        String passord = "";
        for (int i = 0; i < 6; i++) {
            passord += RANDOM_TEGN.charAt(randomIndex());
        }
        return passord;
    }


    public void addEmne() {
        //TODO legg til et emne en bruker er medlem av. Her skal ikke tilgangsrettigheter være
    }

    /**
     * Tar inn en kryptert string, lager en ny string med lengde 32
     *
     * @param krypt1
     * @return
     */
    private String krypterPassord2(String krypt1) {
        int length = krypt1.length();
        int index = 0;
        String dobbelKrypt = "";
        for (int i = 0; i < 32; i++) {
            index = i;
            index %= length;
            dobbelKrypt += krypt1.charAt(index);
        }
        return dobbelKrypt;
    }

    /**
     * Tar inn en string fra brukeren og krypterer passordet.
     *
     * @param pw passord skrevet inn av bruker
     * @return String som kryptert passord
     * @author vimCnett
     * @see "The Java Programming Language"
     */
    private String krypterPassord(String pw) {
        String alphaString = ("abcdefghijklmnopqrstuvwxyz").toUpperCase();
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz" + alphaString + "123567890").toCharArray();
        int length = alphabet.length;
        String kryptertPassord = "";
        int verdi = 0;
        int verdi2 = 0;
        char part1;
        char part2;
        char part3;
        char part4;
        for (int i = 0; i < pw.length(); i++) {
            verdi = (int) pw.charAt(i);
            verdi *= verdi;

            verdi2 = i * verdi * verdi2;

            part1 = (char) alphabet[(verdi % length)];
            part2 = (char) alphabet[((i * 103) % length)];
            part3 = (char) alphabet[(int) ((i * verdi2 * 708) % length)];
            part4 = (char) alphabet[(((1337 * verdi2 + verdi) % 713) % length)];

            kryptertPassord += part1;
            kryptertPassord += part2;
            kryptertPassord += part3;
            kryptertPassord += part4;
        }
        return krypterPassord2(kryptertPassord);
    }
}

