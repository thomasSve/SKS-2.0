package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by Thomas on 15.01.14.
 */
public class DelEmne {
    private int nr;
    private char semester;
    private int koe_id;
    private Ovingsopplegg ovinger;
    private boolean koe_status;
    private ArrayList<Oving> studentovinger;


    public DelEmne() {
    }

    public DelEmne(int nr, char semester, int koe_id, Ovingsopplegg ovinger) {
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public char getSemester() {
        return semester;
    }

    public void setSemester(char semester) {
        this.semester = semester;
    }

    public int getKoe_id() {
        return koe_id;
    }

    public void setKoe_id(int koe_id) {
        this.koe_id = koe_id;
    }

    public Ovingsopplegg getOvinger() {
        return ovinger;
    }

    public void setOvinger(Ovingsopplegg ovinger) {
        this.ovinger = ovinger;
    }

    public boolean isKoe_status() {
        return koe_status;
    }

    public void setKoe_status(boolean koe_status) {
        this.koe_status = koe_status;
    }

    public ArrayList<Oving> getStudentovinger() {
        return studentovinger;
    }

    public void setStudentovinger(ArrayList<Oving> studentovinger) {
        this.studentovinger = studentovinger;
    }

}
