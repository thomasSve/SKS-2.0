package no.hist.tdat.javabeans;

import java.util.ArrayList;

/**
 * Created by Thomas on 23.01.14.
 */
public class EmnerBeans {
    Emne valgtEmne;
    ArrayList<Emne> valgt = new ArrayList<>();    //alle

    public EmnerBeans() {
    }

    public ArrayList<Emne> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Emne> valgt) {
        this.valgt = valgt;
    }

    public void leggTil(Emne b) {
        valgt.add(b);
        //fellesEmne = finnFellesEmner();
    }

    public Emne getValgtBruker() {
        return valgtEmne;
    }

    public void setValgtBruker() {
        System.out.println(valgt.size());
        for (int i = 0; i < valgt.size(); i++) {
            Emne b1 = valgt.get(i);
            valgtEmne = b1;
            return;
        }
    }
}

