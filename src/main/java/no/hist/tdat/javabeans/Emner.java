package no.hist.tdat.javabeans;

/**
 * Created by vimCnett
 */
public class Emner {
    private String emneKode;
    private String emneNavn;
    private Ovingsopplegg ovinger;
    private boolean koeAktiv;

    public Emner(String kode, String navn, Ovingsopplegg ovinger) {
        this.emneKode = kode;
        this.emneNavn = navn;
        this.ovinger = ovinger;
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

    public Ovingsopplegg getOvinger() {
        return ovinger;
    }

    public void setOvinger(Ovingsopplegg ovinger) {
        this.ovinger = ovinger;
    }

    public boolean isKoeAktiv() {
        return koeAktiv;
    }

    public void setKoeAktiv(boolean koeAktiv) {
        this.koeAktiv = koeAktiv;
    }
}
