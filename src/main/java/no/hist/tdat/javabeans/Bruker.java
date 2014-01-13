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

    public Bruker(){}

    /**
     * Konstruktør for innlogging
     * @param mail
     * @param passord
     */
    public Bruker(String mail, String passord){
        this.mail = mail;
        this.passord = krypterPassord(passord);
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

    /**
     * Tar inn en kryptert string, lager en ny string med lengde 32
     * @param krypt1
     * @return
     */
    private String krypterPassord2(String krypt1){
        int length = krypt1.length();
        int index = 0;
        String dobbelKrypt= "";
        for (int i = 0; i < 32; i++) {
            index = i;
            index%=length;
            dobbelKrypt+=krypt1.charAt(index);
        }
        return dobbelKrypt;
    }

    /**
     *Tar inn en string fra brukeren og krypterer passordet.
     * @param pw passord skrevet inn av bruker
     * @see "The Java Programming Language"
     * @return String som kryptert passord
     * @author vimCnett
     */
    private String krypterPassord(String pw){
        String alphaString = ("abcdefghijklmnopqrstuvwxyz").toUpperCase();
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz"+alphaString+"123567890").toCharArray();
        int length = alphabet.length;
        String kryptertPassord ="";
        int verdi = 0;
        int verdi2 = 0;
        char part1;
        char part2;
        char part3;
        char part4;
        for (int i = 0; i <pw.length() ; i++) {
            verdi = (int)pw.charAt(i);
            verdi*=verdi;

            verdi2=i*verdi*verdi2;

            part1= (char)alphabet[(verdi%length)];
            part2= (char)alphabet[((i*103)%length)];
            part3= (char)alphabet[(int)((i*verdi2*708)%length)];
            part4= (char)alphabet[(((1337*verdi2+verdi)%713)%length)];

            kryptertPassord+=part1;
            kryptertPassord+=part2;
            kryptertPassord+=part3;
            kryptertPassord+=part4;
        }
        return krypterPassord2(kryptertPassord);
    }
}

