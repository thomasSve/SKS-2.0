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
    public void leggTilBruker(Bruker bruker) {
        databaseConnector.leggTilBruker(bruker);
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

}
/**

 *

 */