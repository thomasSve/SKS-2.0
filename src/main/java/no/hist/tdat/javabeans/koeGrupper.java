package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by Thomas on 15.01.14.
 */
public class koeGrupper {
    private String klokkeslett;
    private String gruppeLeder;
    private String sitteplass;
    private ArrayList<String>medlemmer;
    private ArrayList<Integer>ovinger;


    public koeGrupper(){}
    public koeGrupper(String klokkeslett, String gruppeLeder, String sitteplass,ArrayList<String>medlemmer, ArrayList<Integer>ovinger){
        this.gruppeLeder = gruppeLeder;
        this.klokkeslett = klokkeslett;
        this.medlemmer = medlemmer;
        this.sitteplass = sitteplass;
        this.ovinger = ovinger;
    }
    public koeGrupper(String sitteplass, String klokkeslett, String gruppeLeder, ArrayList<Integer>ovinger){
        this.gruppeLeder = gruppeLeder;
        this.klokkeslett = klokkeslett;
        this.sitteplass = sitteplass;
        this.ovinger = ovinger;
    }

    public String getKlokkeslett() {
        return klokkeslett;
    }

    public void setKlokkeslett(String klokkeslett) {
        this.klokkeslett = klokkeslett;
    }

    public String getGruppeLeder() {
        return gruppeLeder;
    }

    public void setGruppeLeder(String gruppeLeder) {
        this.gruppeLeder = gruppeLeder;
    }

    public String getSitteplass() {
        return sitteplass;
    }

    public void setSitteplass(String sitteplass) {
        this.sitteplass = sitteplass;
    }

    public ArrayList<String> getMedlemmer() {
        return medlemmer;
    }

    public void setMedlemmer(ArrayList<String> medlemmer) {
        this.medlemmer = medlemmer;
    }

    public ArrayList<Integer> getOvinger() {
        return ovinger;
    }

    public void setOvinger(ArrayList<Integer> ovinger) {
        this.ovinger = ovinger;
    }
}
