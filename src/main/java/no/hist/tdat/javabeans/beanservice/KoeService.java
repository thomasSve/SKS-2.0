package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Koe;
import no.hist.tdat.javabeans.KoeGrupper;
import no.hist.tdat.javabeans.Plassering;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Henriette
 */
@Service
public class KoeService {

    @Autowired
    DatabaseConnector databaseConnector;

    public ArrayList<Plassering> getPlasseringer() {
        return databaseConnector.finnAllePlasseringer();
    }

    public int getAntBord(String plasseringNavn) {
        return databaseConnector.getAntallBord(plasseringNavn);
    }

    public ArrayList<KoeGrupper> getKoe(int koeId) {
        return databaseConnector.getKoe(koeId);
    }

    public ArrayList<KoeBruker> getBrukerIKo(String mail, int koe_Id) {
        return databaseConnector.hentBrukerFraKo(mail, koe_Id);
    }

    public boolean leggTilIKo(KoeGrupper koeGruppe, DelEmne delEmne, int koe_id) {
        return databaseConnector.leggTilIKo(koeGruppe, delEmne, koe_id);

    }
    public DelEmne hentDelEmneStatus(int koeId){
        return databaseConnector.getKoeObjekt(koeId);
    }
}
