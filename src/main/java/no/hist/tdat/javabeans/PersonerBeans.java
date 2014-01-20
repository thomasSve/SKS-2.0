package no.hist.tdat.javabeans;


import java.util.ArrayList;

/**
 * Created by vimCnett
 * Brukes til å skille mellom ALLE brukere evt studenter, og VALGTE brukere
 */


public class PersonerBeans {
    ArrayList<Bruker> valgt = new ArrayList<>();    //alle
    ArrayList<Emne> brukteEmner = new ArrayList<>();

    public ArrayList<Emne> getBrukteEmner() {
        return brukteEmner;
    }

    public void setBrukteEmner(ArrayList<Emne> e) {
        brukteEmner = e;
    }


    public ArrayList<Bruker> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Bruker> brukere) {
        valgt = brukere;
    }


    /**
     * Finner felles fag for alle valgte elever
     */
    public ArrayList<Emne> finnFellesEmner() {
        ArrayList<Emne> emne = valgt.get(0).getEmne();
        ArrayList<Emne> felles = new ArrayList<Emne>();

        if (emne != null && emne.size() > 1) {
            for (int i = 0; i < emne.size(); i++) { //går gjennom alle emne til 1. pers
                String emneKode = emne.get(i).getEmneKode();

                for (int j = 1; j < valgt.size(); j++) {    //går gjennom alle personer
                    Bruker b = valgt.get(j);
                    ArrayList<Emne> denneEmne = b.getEmne();

                    for (int k = 0; k < denneEmne.size(); k++) {
                        Emne e = denneEmne.get(k);
                        String denneKode = e.getEmneKode();

                        if (emneKode.equals(denneKode)) {
                            felles.add(e);
                        }
                    }
                }
            }
        }
        return felles;
    }
}
