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

    /**
     *
     * @param mail      Bruker mail for å finne spesifikk bruker i koe
     * @param koe_Id    bruker koe_id for å finne spesikk koe
     * @return          Returnerer en liste over brukere som står i kø.
     */
    public ArrayList<KoeGrupper> getBrukerIKo(String mail, int koe_Id, int koe_plass) {
        return databaseConnector.hentBrukerFraKo(mail, koe_Id, koe_plass);
    }

    public boolean leggTilIKo(KoeGrupper koeGruppe, DelEmne delEmne, String oving) {
        System.out.println("DelEmne: " + delEmne.getEmneKode());
        int gruppeID = databaseConnector.finnMaxGruppeId(koeGruppe.getKoe_id());
        if(databaseConnector.leggTilKoGruppe(koeGruppe, oving, gruppeID)){
           int leder = 0;
           for(int i = 0; i<koeGruppe.getMedlemmer().size(); i++){
               leder = 0;
               if(i==0){
                   leder = 1;
               }
               databaseConnector.leggTilGruppeMedlem(koeGruppe.getKoe_id(),gruppeID,koeGruppe.getMedlemmer().get(i).getMail(), leder);
           }
           for(int i = 0; i<koeGruppe.getOvingnr().size(); i++){
               int ovingID = databaseConnector.finnOving_id(koeGruppe.getOvingnr().get(i), delEmne.getEmneKode(), delEmne.getNr());             //oving_nr, emnekode, delemne_nr
               databaseConnector.leggTilGruppeOving(gruppeID, delEmne.getKoe_id(), ovingID);
           }
           return true;
        }
        return false;
    }
    public DelEmne hentDelEmneStatus(int koeId){
        return databaseConnector.getKoeObjekt(koeId);
    }
    public Koe hentKoeId (){
        return databaseConnector.delemneIKoe();
    }
}
