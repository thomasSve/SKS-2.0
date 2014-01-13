package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by vimCnett
 */
public class Ovingsopplegg {
    private int totaltOvinger;
    private int kravOvinger;
    private ArrayList<AvanserteKrav> avanserteKrav;

    public Ovingsopplegg(int tot, int krav, ArrayList<AvanserteKrav> avanserte) {
        totaltOvinger = tot;
        kravOvinger = krav;
        avanserteKrav = avanserte;
    }

    public int getTotaltOvinger() {
        return totaltOvinger;
    }

    public void setTotaltOvinger(int totaltOvinger) {
        this.totaltOvinger = totaltOvinger;
    }

    public int getKravOvinger() {
        return kravOvinger;
    }

    public void setKravOvinger(int kravOvinger) {
        this.kravOvinger = kravOvinger;
    }

    public ArrayList<AvanserteKrav> getAvanserteKrav() {
        return avanserteKrav;
    }

    public void setAvanserteKrav(ArrayList<AvanserteKrav> avanserteKrav) {
        this.avanserteKrav = avanserteKrav;
    }
}
