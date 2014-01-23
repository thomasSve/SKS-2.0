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

    public String genererStartStopKnapp(DelEmne delEmne){
        int koe_id = delEmne.getKoe_id();
        String output ="";
        System.out.println(delEmne.getDelEmneNavn()+", "+delEmne.isKoe_status());
        if (!delEmne.isKoe_status()) {
            delEmne.setKoe_status(true);
            output+="<input type=\"button\" onclick=\"startStoppKoeKnapp();startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-danger\" id=\"stoppKoe\" value=\"Stopp Køen\">\n";
                    // + "<button class=\"btn btn-sm btn-primary\"  onclick=\"settIKo(" + koe_id + ")\">Still i k&oslash;</button>"
        } else {
            delEmne.setKoe_status(false);
            output+="<input type=\"button\" onclick=\"startStoppKoeKnapp();startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-success\" id=\"startKoe\" value=\"Start Køen\">\n";
        }
        return output;
    }

    public String genererKoeOversikt(Koe koe){
        ArrayList<KoeGrupper> grupper = koe.getGrupper();
        String output = "";
        output+="<table class='table table-hover'>";
        output+="<thead>"
            +"<tr>"
                +"<th>Tid</th>"
                +"<th>Navn</th>"
                +"<th>Kommentar</th>"
                +"<th>Sitteplass</th>"
                +"<th></th>"
            +"</tr>"
            +"</thead>"
            +"<tbody>";
        for (int i = 0; i < grupper.size(); i++) {
            KoeGrupper gruppe =  grupper.get(i);
            if(gruppe.getFaarHjelp()!=null){
                output+="<tr class='success'>"
                +"<td><a class=' btn btn-success btn-sm ' title='"+gruppe.getFaarHjelp()+"'>"
                +"<i class='glyphicon glyphicon-eye-open'></i> </a></td>";
            }
            if(gruppe.getFaarHjelp()==null){
                output+="<tr>"
                +"<td>"+gruppe.getKlokkeslett()+"</td>";
            }

        output+="<td>"+gruppe.getMedlemmer().get(0).getFornavn()+" "+gruppe.getMedlemmer().get(0).getEtternavn()+"</td>"
        +"<td>"+gruppe.getKommentar()+"</td>"
        +"<td>"+gruppe.getSitteplass()+", bord "+gruppe.getBordnr()+"</td>"
        +"<td>"
        +"<div class='btn-group' id='"+gruppe.getGruppeID()+"'>"
        +"<button class='btn btn-primary' data-task='choose' title='Velg' onclick='location.href='godkjennOving.htm''><i class='glyphicon glyphicon-edit'></i>"
        +"</button>"
        +"<button class='btn btn-warning' data-task='edit' title='Endre &oslash;vinger' onclick='endreBruker(this.parentNode.id)'><i class='glyphicon glyphicon-edit'></i>"
        +"</button>"
        +"<button class='btn btn-danger' data-task='remove' title='Fjern' onclick='slettBruker(this.parentNode.id)'><i class='glyphicon glyphicon-remove'></i>"
        +"</button>"
        +"</div>"
        +"</td>"
        +"</tr>";
        }
        output+="</tbody>"
        +"</table>";
        return output;
    }
}
