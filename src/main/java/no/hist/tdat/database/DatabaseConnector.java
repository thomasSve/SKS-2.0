package no.hist.tdat.database;

import no.hist.tdat.database.verktoy.*;
import no.hist.tdat.javabeans.*;
import no.hist.tdat.koe.Koe;
import no.hist.tdat.koe.KoeBruker;
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
    private final String getKoeSQL = "";
    private final String hentGruppeOvingerSQL = "SELECT oving_nr FROM gruppe_oving NATURAL JOIN oving WHERE gruppe_oving.gruppe_id=?";
    private final String hentGruppeMedlemmerSQL = "SELECT * FROM gruppe NATURAL JOIN brukere WHERE gruppe_id=? ORDER BY gruppe.leder DESC";
    private final String hentkoeGrupperSQL = "Select * FROM koe_gruppe WHERE koe_id= ? ORDER BY koe_gruppe.koe_plass ASC";
    private final String brukerOvingerSQL = "SELECT * FROM oving_brukere NATURAL JOIN oving WHERE oving_brukere.mail = ? AND emnekode = ? AND delemne_nr = ?";
    private final String brukerDelemnerSQL = "SELECT * FROM emner JOIN delemne ON emner.emnekode = delemne.emnekode JOIN emner_brukere ON delemne.emnekode = emner_brukere.emnekode AND emner_brukere.mail=? AND delemne.emnekode=?";
    private final String brukerEmnerSQL = "SELECT emner.emnekode, emner.emnenavn FROM emner, emner_brukere WHERE emner.emnekode = emner_brukere.emnekode AND emner_brukere.mail = ?";
    private final String loggInnBrukerSQL = "SELECT * FROM brukere WHERE mail = ? AND passord = ?";
    private final String leggTilBrukerSQL = "INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES (?,?,?,?,?,?)";
    private final String oppdaterBrukerSQL = "UPDATE brukere SET mail = ?, rettighet_id = ?, fornavn = ?, etternavn = ?, aktiv = ? WHERE mail = ?";
    private final String finnBrukerSQL = "SELECT * FROM brukere WHERE mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";
    private final String slettBrukerSQL = "DELETE FROM brukere WHERE mail = ?";
    private final String finnAlleDeltakereSQL = "SELECT * FROM brukere, emner_brukere WHERE brukere.mail = emner_brukere.mail AND emner_brukere.emnekode = ? AND brukere.rettighet_id = 1 AND brukere.mail != ?";
    private final String endrePassordSQL = "UPDATE brukere SET passord = ? WHERE mail LIKE ? ";
    private final String endreKoeStatusSQL = "UPDATE koe SET aapen = ? WHERE koe_id = ?";
    private final String finnStudentSQL = "SELECT * FROM brukere WHERE rettighet_id=1 AND mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";
    private final String hentEmnerForBrukerSQL = "SELECT * FROM emner_brukere JOIN emner ON emner_brukere.emnekode = emner.emnekode WHERE mail LIKE ?";
    private final String finnAllePlasserSQL = "SELECT * FROM plassering";
    private final String oppdaterOvingSQL = "UPDATE oving_brukere SET godkjent = ?, godkjent_av = ?, godkjent_tid = ? WHERE mail = ? AND oving_id = ?";
    private final String finnOvingerSQL = "SELECT * FROM koe_brukere, brukere WHERE koe_brukere.mail = brukere.mail AND koe_brukere.mail = ? AND koe_brukere.koe_id = ?";
    private final String finnAntBordSQL = "SELECT ant_bord FROM plassering WHERE plassering_navn = ?";
    private final String leggTilIKoSQL = "INSERT INTO koe_gruppe (koe_id, gruppe_id, plassering_navn, bordnummer, ovingsnummer, info, koe_plass) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String finnDelEmneSQL = "SELECT * FROM delemne WHERE koe_id LIKE ?";
    private final String hentKoeObjektSQL = "SELECT * FROM koe WHERE koe_id LIKE ? ";
    private final String leggTilEmneSQL = "INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES (?,?,?)";
    private final String fjernEmneSQL = "DELETE FROM emner_brukere WHERE mail = ? AND emnekode = ?";
    private final String settStudassSQL = "INSERT INTO delemne_brukere(mail,emnekode,delemne_nr) VALUES (?, (SELECT emnekode FROM delemne WHERE delemnenavn LIKE ?), (SELECT delemne_nr FROM delemne WHERE delemnenavn LIKE ?))";
    private final String fjernStudassSQL = "DELETE FROM delemne_brukere WHERE mail LIKE ? AND emnekode LIKE (SELECT emnekode FROM delemne WHERE delemnenavn LIKE ?) AND delemne_nr = (SELECT delemne_nr FROM delemne WHERE delemnenavn LIKE ?)";

    @Autowired
    private DataSource dataKilde; //Felles datakilde for alle spørringer.

    public ArrayList<KoeBruker> hentBrukerFraKo(String mail, int koe_id) {
        if (mail == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<KoeBruker> koBrukerList = con.query(finnOvingerSQL, new KoeBrukerKoordinerer(), mail, koe_id);
        ArrayList<KoeBruker> output = new ArrayList<>();
        for (KoeBruker denne : koBrukerList) {
            output.add(denne);
        }
        return output;
    }

    public ArrayList<DelEmne> hentDelemner(Bruker bruker, Emne emne) {
        if (bruker == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<DelEmne> delemneList = con.query(brukerDelemnerSQL, new DelEmneKoordinerer(), bruker.getMail(), emne.getEmneKode());
        ArrayList<DelEmne> output = new ArrayList<>();
        for (DelEmne denne : delemneList) {
            output.add(denne);
        }
        return output;
    }

    public ArrayList<Oving> hentStudOvinger(Bruker bruker, Emne emne, DelEmne delemne) {
        if (bruker == null) {
            return null;
        }

        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Oving> ovingList = con.query(brukerOvingerSQL, new OvingKoordinerer(), bruker.getMail(), emne.getEmneKode(), delemne.getNr());
        ArrayList<Oving> output = new ArrayList<>();//TODO Hent alle ovinger for alle fag!
        for (Oving denne : ovingList) {
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
                    bruker.getMail(),
                    bruker.getRettighet(),
                    bruker.getFornavn(),
                    bruker.getEtternavn(),
                    bruker.getAktiv(),
                    mail);
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

    public boolean oppdaterOving(Oving oving, String mail, int oving_id) {
        if (oving == null) {
            return false;
        } else {
            JdbcTemplate con = new JdbcTemplate(dataKilde);
            con.update(oppdaterOvingSQL,
                    mail, oving_id,
                    oving.denneErGodkjent(),
                    oving.getGodkjentAv(),
                    oving.getGodkjentTid());
            return true;
        }
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
        input += soeketekst + "%";
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
    public ArrayList<Emne> hentMineEmner(Bruker bruker) {
        if (bruker == null) {
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Emne> emneList = con.query(brukerEmnerSQL, new EmneKoordinerer(), bruker.getMail());
        ArrayList<Emne> res = new ArrayList<>();
        for (Emne emne : emneList) {
            res.add((Emne) emne);
        }


        if (res.size() > 0) {
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

    public boolean endreKoeStatus(int koe_id, int status) {
        if (koe_id == 0 || status > 1) {
            return false;
        }
        System.out.println("Kø id: " + koe_id + ", Ny Status: " + status);
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
        List<Bruker> brukerList = con.query(finnAlleDeltakereSQL, new BrukerKoordinerer(), emnekode, mail);
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
        List<Emne> emneList = con.query(hentEmnerForBrukerSQL, new EmneKoordinerer(), mail);
        ArrayList<Emne> res = new ArrayList<>();
        for (Emne emne : emneList) {
            res.add(emne);
        }
        return res;
    }

    /**
     * @return Liste over alle mulige plasseringer
     * @author henriette
     * Henter ut alle mulige plasser man kan sitte for å få øving retta
     */
    public ArrayList<Plassering> finnAllePlasseringer() {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Plassering> plassListe = con.query(finnAllePlasserSQL, new PlasseringKoordinerer());
        ArrayList<Plassering> res = new ArrayList<Plassering>();

        for (Plassering plass : plassListe) {
            res.add(plass);
        }
        return res;
    }

    /**
     * @param plasseringNavn plassen som har bordet man sitter på
     * @return alle mulige bord på den valgte plassen
     * @author henriette
     * Henter ut alle mulige bord på en gitt plassering
     */
    public int getAntallBord(String plasseringNavn) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Plassering> plassListe = con.query(finnAntBordSQL, new PlasseringKoordinerer(), plasseringNavn);
        ArrayList<Plassering> res = new ArrayList<Plassering>();
        for (Plassering plass : plassListe) {
            res.add(plass);
        }
        return plassListe.get(0).getAnt_bord();
    }

    /**
     * legger til emne for bruker, gitt mail
     *
     * @param mail id-mail, emnekode, foreleser
     * @return boolean
     */
    public boolean leggTilEmne(String emnekode, String mail, int foreleser) {
        if (mail == null || emnekode == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        try {
            con.update(leggTilEmneSQL, emnekode, mail, foreleser);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Fjerner tilgang til emne
     *
     * @param mail id-mail og emnekode
     * @return boolean
     */
    public boolean fjernEmne(String emnekode, String mail) {
        if (mail == null || emnekode == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        try {
            con.update(fjernEmneSQL, mail, emnekode);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public ArrayList<Bruker> getBrukereIKoeGruppe(int gruppeId) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Bruker> brukereFraDb = con.query(hentGruppeMedlemmerSQL, new BrukerKoordinerer(), gruppeId);
        ArrayList<Bruker> brukere = new ArrayList<>();
        Bruker bruker;
        for (int i = 0; i < brukereFraDb.size(); i++) {
            bruker = brukereFraDb.get(i);
            brukere.add(bruker);
        }
        return brukere;
    }

    public ArrayList<Integer> getOvingerIKoeGruppe(int gruppeId) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<Oving> ovingerFraDb = con.query(hentGruppeOvingerSQL, new KoeOvingKoordinerer(), gruppeId);
        ArrayList<Integer> ovinger = new ArrayList<>();
        Oving oving;
        for (int i = 0; i < ovingerFraDb.size(); i++) {
            oving = ovingerFraDb.get(i);
            ovinger.add(oving.getOvingnr());
        }
        return ovinger;
    }

    public ArrayList<KoeGrupper> getKoe(int koeId) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<KoeGrupper> grupperFromDb = con.query(hentkoeGrupperSQL, new KoeGruppeKoordinerer(), koeId);
        ArrayList<KoeGrupper> grupper = new ArrayList<>();
        KoeGrupper koeGrupper;
        for (int i = 0; i < grupperFromDb.size(); i++) {
            koeGrupper = grupperFromDb.get(i);

            koeGrupper.setMedlemmer(getBrukereIKoeGruppe(koeGrupper.getGruppeID()));
            koeGrupper.setOvinger(getOvingerIKoeGruppe(koeGrupper.getGruppeID()));
            grupper.add(koeGrupper);
        }
        if (grupper.size() > 0) {
            return grupper;
        }
        return null;
    }

    public DelEmne hentDelEmne(int koe_id) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<DelEmne> delEmne = con.query(finnDelEmneSQL, new DelEmneKoordinerer(), koe_id);
        return delEmne.get(0);
    }

    /**
     * @param koeGruppe
     * @param delEmne
     * @param koe_plass
     * @return true or false, om updaten gjekk gjennom eller ikke
     * Author Thomas
     */
    public boolean leggTilIKo(KoeGrupper koeGruppe, DelEmne delEmne, int koe_plass) {
        if (delEmne == null || koeGruppe == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(
                leggTilIKoSQL,                                   //koe_id, mail, plassering, ovingsnummer, koe_plass
                delEmne.getKoe_id(),
                koeGruppe.getGruppeLeder().getMail(),
                koeGruppe.getSitteplass(),
                koeGruppe.getOvinger(),
                koe_plass
        );
        return true;
    }
    public DelEmne getKoeObjekt(int koe_id){
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        List<DelEmne> delEmne = con.query(hentKoeObjektSQL, new KoestatusDelEmneKoordinerer(), koe_id);
        return delEmne.get(0);
    }

    /**
     * Setter studass
     *
     * @param mail id-mail og emnekode og delemne
     * @return boolean
     */
    public boolean settStudass(String emnenavn,String mail) {
        if (mail == null || emnenavn == null) {
            return false;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        try {
            con.update(settStudassSQL, mail, emnenavn, emnenavn);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Fjerner studass
     *
     * @param mail id-mail, delemne
     * @return boolean
     */
    public boolean fjernStudass(String navn, String mail) {
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        try {
            con.update(fjernStudassSQL, mail, navn, navn);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}

