package no.hist.tdat.javabeans;


import java.util.ArrayList;

/**
 * Created by vimCnett
 * Brukes til å skille mellom ALLE brukere evt studenter, og VALGTE brukere
 */
public class PersonerBeans {
    Bruker valgtBruker;
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
     * Legger til bruker i arraylisten valgt
     * @param b bruker objekt
     */
    public void leggTil(Bruker b) {
        valgt.add(b);
        //fellesEmne = finnFellesEmner();
    }

    public Bruker getValgtBruker() {
        return valgtBruker;
    }

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
