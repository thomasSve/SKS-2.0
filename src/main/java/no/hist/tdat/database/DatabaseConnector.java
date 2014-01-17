package no.hist.tdat.database;

import no.hist.tdat.database.verktoy.*;
import no.hist.tdat.javabeans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseConnector kobler til databasen og gjør spørring, for deretter å stenge tilkoblingen.
 *
 * @author VimCnett
 */

@Service
public class DatabaseConnector {
    private static final String QUERY_ERROR = "FEIL I SPØRRING";
    private static final String CONNECTION_ERROR = "FEIL VED TILKOBLING TIL DATABASE";
    private static final Integer ACTIVE = 1;

    // **** Legger alle Queryes her. Ikke fordi vi må, men fordi Grethe liker det sånn...*/ //TODO remove this


    private final String brukerOvingerSQL = "SELECT * FROM oving_brukere LEFT JOIN oving ON oving_brukere.oving_id=oving.oving_id WHERE oving_brukere.mail = ? AND emnekode = ? AND delemne_nr = ?";
    private final String brukerDelemnerSQL = "SELECT * FROM emner JOIN delemne ON emner.emnekode = delemne.emnekode JOIN emner_brukere ON delemne.emnekode = emner_brukere.emnekode AND emner_brukere.mail=? AND delemne.emnekode=?";
    private final String brukerEmnerSQL = "SELECT emner.emnekode, emner.emnenavn FROM emner, emner_brukere WHERE emner.emnekode = emner_brukere.emnekode AND emner_brukere.mail = ?";
    private final String loggInnBrukerSQL = "SELECT * FROM brukere WHERE mail = ? AND passord = ?";
    private final String leggTilBrukerSQL = "INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES (?,?,?,?,?,?)";
    private final String oppdaterBrukerSQL = "UPDATE brukere SET mail = ?, rettighet_id = ?, fornavn = ?, etternavn = ?, passord = ?, aktiv = ? WHERE mail = ?";
    private final String finnBrukerSQL = "SELECT * FROM brukere WHERE mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";
    private final String slettBrukerSQL = "DELETE FROM brukere WHERE mail = ?";
    private final String leggTilIKoSQL = "INSERT INTO koe_brukere (koe_id, mail, plassering, ovingsnummer, koe_plass) VALUES (?,?,?,?,?)";
    private final String finnAlleDeltakereSQL = "SELECT * FROM brukere, emner_brukere WHERE brukere.mail = emner_brukere.mail AND emner_brukere.emnekode = ? AND brukere.rettighet_id = 1 AND brukere.mail != ?";
    private final String endrePassordSQL = "UPDATE brukere SET passord = ? WHERE mail LIKE ? ";
    private final String endreKoeStatusSQL = "UPDATE koe SET aapen = ? WHERE koe_id = ?";
    private final String finnStudentSQL = "SELECT * FROM brukere WHERE rettighet_id=1 AND mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";
    private final String hentEmnerForStudSQL = "SELECT * FROM emner_brukere JOIN emner ON emner_brukere.emnekode = emner.emnekode WHERE mail LIKE ?";
    private final String finnAllePlasserSQL = "SELECT * FROM plassering";
    private final String finnAntBordSQL = "SELECT ant_bord FROM plassering WHERE romnr = ?";
    private final String leggTilEmneSQL = "INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES (?,?,?)";


    @Autowired
    private DataSource dataKilde; //Felles datakilde for alle spørringer.


    public ArrayList<DelEmne> hentDelemner(Bruker bruker,Emne emne){
        if (bruker==null){
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<DelEmne> delemneList = con.query(brukerDelemnerSQL, new DelEmneKoordinerer(), bruker.getMail(),emne.getEmneKode());
        ArrayList<DelEmne> output = new ArrayList<>();
        for(DelEmne denne : delemneList){
            output.add(denne);
        }
        return output;
    }
    public ArrayList<Oving> hentStudOvinger(Bruker bruker,Emne emne,DelEmne delemne){
        if (bruker==null){
            return null;
        }

        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Oving> ovingList = con.query(brukerOvingerSQL, new OvingKoordinerer(), bruker.getMail(),emne.getEmneKode(),delemne.getNr());
       ArrayList<Oving> output = new ArrayList<>();//TODO Hent alle ovinger for alle fag!
       for(Oving denne : ovingList){
           output.add(denne);
       }
        return output;
    }

    /**
     * Denne klassen skal kun brukes av TESTPROGRAMMET ikke noe annet.
     *
     * @param dataKilde testdatakilde
     */
    public void setDataKilde(DataSource dataKilde) { //TODO (OR NOT TODO) IKKE LOV TIL Å BRUKE!!!!!!!!!!
        this.dataKilde = dataKilde;
    }

    /**
     * Legger til en bruker i databasen
     *
     * @param bruker
     * @return true om den blir lagt til, ellers false
     */
    public boolean leggTilBruker(Bruker bruker) throws org.springframework.dao.DuplicateKeyException {
        if (bruker == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(leggTilBrukerSQL,
                bruker.getMail(),
                bruker.getRettighet(),
                bruker.getFornavn(),
                bruker.getEtternavn(),
                bruker.getPassord(),
                bruker.getAktiv());
        return true;
    }

    /**
     * Oppdatterer en spesifikk bruker
     *
     * @param bruker Den brukeren du il endre
     * @param mail   mailen til den du skal endre, denne er i tilfelle man endrer mail
     * @return true om bruker blir oppdatert ellers false
     */
    public boolean oppdaterBruker(Bruker bruker, String mail) {
        if (bruker == null) {
            return false;
        } else {
            JdbcTemplate con = new JdbcTemplate(dataKilde);
            con.update(oppdaterBrukerSQL,
                    mail,
                    bruker.getRettighet(),
                    bruker.getFornavn(),
                    bruker.getEtternavn(),
                    bruker.getPassord(),
                    bruker.getAktiv());
            return true;
        }
    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     *
     * @param soeketekst Søkeord etter bruker
     * @return ArrayList med bruker objekter eller null om ingen finnes.
     */
    public ArrayList<Bruker> finnBruker(String soeketekst) {
        if (soeketekst == null) {
            return null;
        }
        String input = "%";
        input += soeketekst + "%";
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukerList = con.query(finnBrukerSQL, new BrukerKoordinerer(), input, input, input);
        ArrayList<Bruker> res = new ArrayList<>();

        for (Bruker bruker : brukerList) {
            res.add(bruker);
        }
        return res;
    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     *
     * @param soeketekst Søkeord etter studentr
     * @return ArrayList med bruker objekter eller null om ingen finnes.
     */
    public ArrayList<Bruker> finnStudenter(String soeketekst) {
        if (soeketekst == null) {
            return null;
        }
        String input = "%";
        input += soeketekst+"%";
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukerList = con.query(finnStudentSQL, new BrukerKoordinerer(), input, input, input);
        ArrayList<Bruker> res = new ArrayList<>();

        for (Bruker bruker : brukerList) {
            res.add(bruker);
        }
        return res;
    }

    /**
     * Sjekker om mail og passord korresponderer
     *
     * @param bruker brukerobjekt med kun mail og passord
     * @return nytt brukerobjekt med all brukerinformasjon
     */
    public Bruker loggInn(Bruker bruker) {
        if (bruker == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukerList = con.query(loggInnBrukerSQL, new BrukerKoordinerer(), bruker.getMail(), bruker.getPassord());
        ArrayList<Bruker> res = new ArrayList<>();

        for (Bruker brukerInfo : brukerList) {

            res.add(brukerInfo);
        }

        if (res.size() > 0) {

            return res.get(0);
        }

        return null;
    }
    /**
     * Henter emnene en bruker har
     *
     * @param bruker
     * @return en ArrayList med emner.
     */
    public ArrayList<Emne> hentMineEmner(Bruker bruker){
        if (bruker == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Emne> emneList = con.query(brukerEmnerSQL, new EmneKoordinerer(), bruker.getMail());
        ArrayList<Emne> res = new ArrayList<>();
        for (Emne emne : emneList) {
            res.add((Emne)emne);
        }


        if(res.size() >0){
            return res;
        }
        return null;
    }

    /**
     * Sletter bruker med gitt epost.
     *
     * @param epost eposten til den brukeren som skal slettes fra databasen
     * @return true hvis en eller flere rader fra tabellen har blitt slettet. false hvis ingen rader blir slettet.
     */
    public boolean slettBruker(String epost) {
        if (epost == null)
            return false;
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        int num = con.update(slettBrukerSQL, epost);
        return num > 0;

    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som er lik søkeordet.
     *
     * @param soeketekst Søkeord etter studenter
     * @return objekt av Bruker, eller null om den ikke finnes
     */
    public Bruker finnStudent(String soeketekst) {

        if (soeketekst == null) {
            return null;
        }

        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukerList = con.query(finnStudentSQL, new BrukerKoordinerer(), soeketekst, soeketekst, soeketekst);

        return brukerList.get(0);
    }

    /**
     * Tar inn mailen til brukeren som skal endrest samt det nye passordet.
     *
     * @param passord, det nye passordet
     * @param mail,    mailen til brukeren
     * @return true dersom vellykket
     */
    public boolean endrePassord(String mail, String passord) {
        if (mail == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(endrePassordSQL,
                    passord,
                    mail);
        return true;
    }

    public boolean endreKoeStatus(int koe_id, int status){
        if(koe_id==0||status>1){
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(endreKoeStatusSQL,
                status,
                koe_id);
        return true;
    }
    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     *
     * @param emnekode Hvilket emne man vil sette seg i kø i
     * @return ArrayList med alle studenter i samme emne
     */
    public ArrayList<Bruker> finnAlleDeltakere(String emnekode, String mail) {
        if (emnekode == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukerList = con.query(finnAlleDeltakereSQL, new BrukerKoordinerer(),emnekode, mail);
        ArrayList<Bruker> res = new ArrayList<Bruker>();

        for (Bruker bruker : brukerList) {
            res.add(bruker);
        }
        return res;

    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     *
     * @param mail id-mail
     * @return ArrayList med alle emner
     */
    public ArrayList<Emne> hentEmnerForStud(String mail) {
        if (mail == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Emne> emneList = con.query(hentEmnerForStudSQL, new EmneKoordinerer(), mail);
        ArrayList<Emne> res = new ArrayList<>();
        for (Emne emne : emneList) {
            res.add(emne);
        }

        return res;
    }

    public ArrayList<Plassering> finnAllePlasseringer() {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Plassering> plassListe = con.query(finnAllePlasserSQL, new PlasseringKoordinerer());
        ArrayList<Plassering> res = new ArrayList<Plassering>();

        for (Plassering plass : plassListe) {
            res.add(plass);
        }
        return res;
    }

    public int getAntallBord(String romnr) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Plassering> plassListe = con.query(finnAntBordSQL, new PlasseringKoordinerer(), romnr);
        ArrayList<Plassering> res = new ArrayList<Plassering>();
        for (Plassering plass : plassListe) {
            res.add(plass);
        }
        return plassListe.get(0).getAnt_bord();
    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     *
     * @param mail id-mail
     * @return ArrayList med alle emner
     */
    public boolean leggTilEmne(String emnekode, String mail, int foreleser) {
        if (mail == null || emnekode == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(leggTilEmneSQL, emnekode, mail, foreleser);
        return true;
    }
}

