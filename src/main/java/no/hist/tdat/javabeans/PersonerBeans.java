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

@Component
public class PersonerBeans {
    //ArrayList<Bruker> alle = null;    unødvendig og ressurskrevende?
    ArrayList<Bruker> valgt;

    @Qualifier("databaseConnector")
    @Autowired
    DatabaseConnector databaseConnector;

    public PersonerBeans() {
        valgt = null;
        databaseConnector = new DatabaseConnector();
    }

    public ArrayList<Bruker> getValgt() {
        return valgt;
    }

    public void setValgt(ArrayList<Bruker> brukere) {
        valgt = brukere;
    }

    public Bruker finnStudent(String sok) {
        Bruker b = databaseConnector.finnStudent(sok);
        if (b != null) {
            valgt.add(b);
        }
        return b;
    }
}
