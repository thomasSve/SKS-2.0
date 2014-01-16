package no.hist.tdat.javabeans;


import no.hist.tdat.database.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by vimCnett
 * Brukes til å skille mellom ALLE brukere evt studenter, og VALGTE brukere
 */

public class PersonerBeans {
    ArrayList<Bruker> valgt = new ArrayList<>();    //alle
    ArrayList<Bruker> valgtBruker = new ArrayList<>();  //mer eksplisitt valgte
    ArrayList<Emne> fellesEmne = null;

    public ArrayList<Bruker> getValgtBruker() {
        return valgtBruker;
    }

    public void setValgtBruker(ArrayList<Bruker> br) {
        System.out.println("PersBeans, setter valgt: "+valgtBruker.get(0));
        valgtBruker = br;
    }

    public ArrayList<Emne> getFellesEmne() {
        return fellesEmne;
    }


    public ArrayList<Bruker> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Bruker> brukere) {
        valgt = brukere;
    }

    /**
     * Legger til bruker i arraylisten valgt
     * @param b bruker objekt
     */
    public void leggTil(Bruker b) {
        valgt.add(b);
        //fellesEmne = finnFellesEmner();
    }
/*
    public void setValgtBruker(String mail) {
        System.out.println(valgt.size());
        for (int i = 0; i < valgt.size(); i++) {
            Bruker b1 = valgt.get(i);
            System.out.println(i);
            if (mail.equalsIgnoreCase(b1.getMail())) {
                valgtBruker = b1;
                System.out.println("setter bruker");
                return;
            }
        }
    }*/

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
