package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by vimCnett
 */
public class Emner {
    private String emneKode;
    private String emneNavn;
    private ArrayList<Oving> studentovinger;
    private Ovingsopplegg ovingsregler;

    public Emner(String kode, String navn, Ovingsopplegg ovinger) {
        this.emneKode = kode;
        this.emneNavn = navn;
        this.ovingsregler = ovinger;
    }

    public Emner() {
    }

    public String getEmneKode() {
        return emneKode;
    }

    public void setEmneKode(String emneKode) {
        this.emneKode = emneKode;
    }

    public String getEmneNavn() {
        return emneNavn;
    }

    public void setEmneNavn(String emneNavn) {
        this.emneNavn = emneNavn;
    }

    public ArrayList<Oving> getStudentovinger() {        return studentovinger;    }

    public void setStudentovinger(ArrayList<Oving> studentovinger) {        this.studentovinger = studentovinger;    }

    public Ovingsopplegg getOvingsregler() {        return ovingsregler;    }

    public void setOvingsregler(Ovingsopplegg ovingsregler) {        this.ovingsregler = ovingsregler;    }
}
