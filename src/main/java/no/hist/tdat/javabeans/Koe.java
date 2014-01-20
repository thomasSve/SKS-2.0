package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by ted on 1/20/14.
 */
public class Koe {
    private ArrayList<KoeGrupper> grupper;
    private int koeId;
    private boolean aapen;

    public ArrayList<KoeGrupper> getGrupper() {
        return grupper;
    }

    public void setGrupper(ArrayList<KoeGrupper> grupper) {
        this.grupper = grupper;
    }

    public int getKoeId() {
        return koeId;
    }

    public void setKoeId(int koeId) {
        this.koeId = koeId;
    }

    public boolean isAapen() {
        return aapen;
    }

    public void setAapen(boolean aapen) {
        this.aapen = aapen;
    }
}
