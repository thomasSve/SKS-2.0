package no.hist.tdat.javabeans;

/**
 * Created by Henriette on 15/01/14.
 */
public class Plassering {
    private String romnr;
    private String plassering_navn;
    private int ant_bord;

    public Plassering(){

    }

    public String getRomnr() {
        return romnr;
    }

    public void setRomnr(String romnr) {
        this.romnr = romnr;
    }

    public String getPlassering_navn() {
        return plassering_navn;
    }

    public void setPlassering_navn(String plassering_navn) {
        this.plassering_navn = plassering_navn;
    }

    public int getAnt_bord() {
        return ant_bord;
    }

    public void setAnt_bord(int ant_bord) {
        this.ant_bord = ant_bord;
    }
}
