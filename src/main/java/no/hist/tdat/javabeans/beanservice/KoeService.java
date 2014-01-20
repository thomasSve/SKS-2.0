package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Plassering;
import no.hist.tdat.javabeans.koeGrupper;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Henriette
 */
@Service
public class KoeService {

    @Autowired
    DatabaseConnector databaseConnector;

    public ArrayList<Plassering> getPlasseringer(){
        return databaseConnector.finnAllePlasseringer();
    }

    public int getAntBord(String romnr){
        return databaseConnector.getAntallBord(romnr);

    }


    public ArrayList<koeGrupper> getKoe(int koeId) {
        return databaseConnector.getKoe(koeId);
    }

    public ArrayList<KoeBruker> getBrukerIKo(String mail, int koe_Id) {
        return databaseConnector.hentBrukerFraKo(mail, koe_Id);
    }
}
