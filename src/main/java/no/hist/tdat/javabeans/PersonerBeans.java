package no.hist.tdat.javabeans;

import no.hist.tdat.database.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Kjetil vimCnett
 * Brukes til å skille mellom ALLE brukere evt studenter, og VALGTE brukere
 */

public class PersonerBeans {

    ArrayList<Bruker> valgt;
    ArrayList<Emner> fellesEmner;

    public PersonerBeans() {
        valgt = null;
    }

    public ArrayList<Emner> getFellesEmner() {
        return fellesEmner;
    }

    public ArrayList<Bruker> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Bruker> brukere) {
        valgt = brukere;
    }

    public void leggTil(Bruker b) {
        valgt.add(b);
        fellesEmner = finnFellesEmner();
    }

    public Bruker finnStudent(String sok) {
//        Bruker b = databaseConnector.finnBruker(sok);
//        if (b != null) {
//            valgt.add(b);
//        }
//        return b;
        return null; //TODO fix this
    }

    public void fjernStudent(int nr) {
        if (valgt.size() > 0 && nr != -1) {
            valgt.remove(nr);
        }
    }


    /**
     * Finner felles fag for alle valgte elever
     */
    public ArrayList<Emner> finnFellesEmner() {
        ArrayList<Emner> emner = valgt.get(0).getEmner();
        ArrayList<Emner> felles = new ArrayList<Emner>();

        for (int i = 0; i <emner.size(); i++) { //går gjennom alle emner til 1. pers
            String emneKode = emner.get(i).getEmneKode();

            for (int j = 1; j < valgt.size(); j++) {    //går gjennom alle personer
                Bruker b = valgt.get(j);
                ArrayList<Emner> denneEmner = b.getEmner();

                for (int k = 0; k < denneEmner.size(); k++) {
                    Emner e = denneEmner.get(k);
                    String denneKode = e.getEmneKode();

                    if (emneKode.equals(denneKode)) {
                        felles.add(e);
                    }
                }
            }
        }
        return felles;
    }
}