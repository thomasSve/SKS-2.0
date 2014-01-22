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

    public boolean leggTilIKo(KoeGrupper koeGruppe, DelEmne delEmne) {
       if(databaseConnector.leggTilKoGruppe(koeGruppe)){
           int leder = 0;
           for(int i = 0; i<koeGruppe.getMedlemmer().size(); i++){
               if(i==0){
                   leder = 1;
               }
               databaseConnector.leggTilGruppeMedlem(delEmne.getKoe_id(), koeGruppe.getGruppeID(),koeGruppe.getMedlemmer().get(i).getMail(), leder);
           }
           for(int i = 0; i<koeGruppe.getOvinger().size(); i++){
               databaseConnector.leggTilGruppeOving(koeGruppe.getGruppeID(), delEmne.getKoe_id() ,koeGruppe.getOvinger().get(i).getOving_id());
           }
           return true;
        }
        return false;
    }
    public DelEmne hentDelEmneStatus(int koeId){
        return databaseConnector.getKoeObjekt(koeId);
    }
}
