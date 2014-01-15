package no.hist.tdat.javabeans;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by vimCnett
 * Brukes til å skille mellom ALLE brukere evt studenter, og VALGTE brukere
 */

public class PersonerBeans {
    ArrayList<Bruker> valgt = new ArrayList<>();
    ArrayList<Emne> fellesEmne = null;
    Integer index = 0;
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
<<<<<<< HEAD

    /**
     @Qualifier("databaseConnector")
     @Autowired
     DatabaseConnector databaseConnector;

     public PersonerBeans() {
     valgt = new ArrayList<Bruker>();
     fellesEmne = new ArrayList<Emne>();
     databaseConnector = new DatabaseConnector();
     }
     */
    public ArrayList<Emne> getFellesEmne() {
        return fellesEmne;
=======
    public ArrayList<Emner> getFellesEmner() {
        return fellesEmner;
>>>>>>> 03bd832c63e34e21b1dd46d256c4f6cdec786c02
    }

    public ArrayList<Bruker> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Bruker> brukere) {
        valgt = brukere;
    }

    public void leggTil(Bruker b) {
        valgt.add(b);
<<<<<<< HEAD
        //fellesEmne = finnFellesEmner();
=======
>>>>>>> 03bd832c63e34e21b1dd46d256c4f6cdec786c02
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
