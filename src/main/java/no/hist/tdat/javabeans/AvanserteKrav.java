package no.hist.tdat.javabeans;

/**
 * Created by vimCnett
 */
public class AvanserteKrav {
    private int[] ovingNr;
    private int minstAnt;

    public AvanserteKrav(int[] nr, int min) {
        ovingNr = nr;
        minstAnt = min;
    }

    public int[] getOvingNr() {
        return ovingNr;
    }

    public void setOvingNr(int[] ovingNr) {
        this.ovingNr = ovingNr;
    }

    public int getMinstAnt() {
        return minstAnt;
    }

    public void setMinstAnt(int minstAnt) {
        this.minstAnt = minstAnt;
    }

    public boolean nokOvinger() {

        return true;
    }
}
