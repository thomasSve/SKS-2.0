package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BrukerService {

    @Autowired
    DatabaseConnector databaseConnector;

    /**
     * Legger til en bruker i databasen
     *
     * @param bruker den brukeren du skal legge til i databasen
     * @see
     *
     */
    public boolean leggTilBruker(Bruker bruker) {
        return databaseConnector.leggTilBruker(bruker);
    }

    /**
     * Sjekker om mail og passord korresponderer
     * Brukerobjekt opprettes av mail og passord før denne kalles.
     *
     * @param bruker brukeren som skal logges inn
     * @return boolean, true om mail og passord korresponderer
     */
    public Bruker loggInn(Bruker bruker) {
        return databaseConnector.loggInn(bruker);
    }

    /**
     * Henter en bruker ut fra databasen
     *
     * @param email brukerens epost
     * @return et brukerobjekt basert på databasedata funnet i databasen. Returnerer null hvis ingenting blir funnet.
     */
    public Bruker hentBruker(String email){
        ArrayList<Bruker> brukere = databaseConnector.finnBruker(email);
        return brukere.get(0);
    }

    /**
     * Henter en liste med mulige bruker
     * @param input søkeordet
     * @return ArrayList med bruker objecter, eller null om ingen treffer med søkeordet
     */
    public ArrayList<Bruker> finnBruker(String input){
        return databaseConnector.finnBruker(input);
    }

    /**
     * Sletter en spesifikk bruker
     * @param mail brukerens mail
     * @return true om bruker ble slettet, ellers false
     */
    public boolean slettBruker (String mail){
        return databaseConnector.slettBruker(mail);
    }
    /**
     * Endrer passordet til en bruker
     * @param mail og nytt passord
     * @return true om vellykket
     */
    public boolean endrePassord(String mail, String passord){
        return databaseConnector.endrePassord(mail, passord);
    }



}