package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by Thomas on 15.01.14.
 */
public class KoeGrupper {
    private String klokkeslett; //TODO maybe date?
    private Bruker gruppeLeder;
    private String sitteplass;
    private int bordnr;
    private String kommentar;
    private ArrayList<Bruker>medlemmer;
    private ArrayList<Integer>ovinger;  // hvilke øvinger som ønskes godkjent


    public KoeGrupper(){}
    public KoeGrupper(String klokkeslett, Bruker gruppeLeder, String sitteplass, String kommentar, ArrayList<Bruker> medlemmer, ArrayList<Integer> ovinger){
        this.gruppeLeder = gruppeLeder;
        this.klokkeslett = klokkeslett;
        this.medlemmer = medlemmer;
        this.sitteplass = sitteplass;
        this.ovinger = ovinger;
        this.kommentar = kommentar;
    }
    public KoeGrupper(String sitteplass, String klokkeslett, String kommentar, Bruker gruppeLeder, ArrayList<Integer> ovinger){
        this.gruppeLeder = gruppeLeder;
        this.klokkeslett = klokkeslett;
        this.sitteplass = sitteplass;
        this.ovinger = ovinger;
        this.kommentar = kommentar;
    }

    public String getKlokkeslett() {
        return klokkeslett;
    }

    public void setKlokkeslett(String klokkeslett) {
        this.klokkeslett = klokkeslett;
    }

    public Bruker getGruppeLeder() {
        return gruppeLeder;
    }

    public void setGruppeLeder(Bruker gruppeLeder) {
        this.gruppeLeder = gruppeLeder;
    }

    public String getSitteplass() {
        return sitteplass;
    }

    public void setSitteplass(String sitteplass) {
        this.sitteplass = sitteplass;
    }

    public int getBordnr() {
        return bordnr;
    }

    public void setBordnr(int bordnr) {
        this.bordnr = bordnr;
    }

    public ArrayList<Bruker> getMedlemmer() {
        return medlemmer;
    }

    public void setMedlemmer(ArrayList<Bruker> medlemmer) {
        this.medlemmer = medlemmer;
    }

    public ArrayList<Integer> getOvinger() {
        return ovinger;
    }

    public void setOvinger(ArrayList<Integer> ovinger) {
        this.ovinger = ovinger;
    }

    public String getKommentar() {
        return kommentar;
    }


    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
