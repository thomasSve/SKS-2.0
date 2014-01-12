package no.hist.tdat.database;

import no.hist.tdat.javabeans.Bruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * DatabaseConnector kobler til databasen og gjør spørring, for deretter å stenge tilkoblingen.
 *
 * @author VimCnett
 */
@Component
public class DatabaseConnector {
    private static final String QUERY_ERROR = "FEIL I SPØRRING";
    private static final String CONNECTION_ERROR = "FEIL VED TILKOBLING TIL DATABASE";
    private static final Integer ACTIVE = 1;
    private final String leggTilBrukerSQL = "INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES (?,?,?,?,?,?)";
    private final String oppdaterBrukerSQL = "UPDATE brukere SET mail = ?, rettighet_id = ?, fornavn = ?, etternavn = ?, passord = ?, aktiv = ? WHERE mail = ?";
    private final String finnBrukerSQL = "SELECT * FROM brukere WHERE mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";

    @Autowired
    DataSource dataKilde;

    public void leggTilBruker(Bruker bruker){
        JdbcTemplate con = new JdbcTemplate(dataKilde);
        con.update(leggTilBrukerSQL,
                                    bruker.getMail(),
                                    bruker.getRettighet(),
                                    bruker.getFornavn(),
                                    bruker.getEtternavn(),
                                    bruker.genererPassord(),
                                    ACTIVE);
    }
    /**
     *Oppdatterer en spesifikk bruker
     * @param bruker Den brukeren du il endre
     * @param mail mailen til den du skal endre, denne er i tilfelle man endrer mail
     * @return true om bruker blir oppdatert ellers false
     */
    public boolean oppdaterBruker(Bruker bruker, String mail){
        if(bruker == null){
            return false;
        }else{
            JdbcTemplate con = new JdbcTemplate(dataKilde);
            con.update(oppdaterBrukerSQL,
                    mail,
                    bruker.getRettighet(),
                    bruker.getFornavn(),
                    bruker.getEtternavn(),
                    bruker.genererPassord(),
                    ACTIVE);
            return true;
        }
    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     * @param soeketekst Søkeord etter bruker
     * @return ArrayList med bruker objekter eller null om ingen finnes.
     */
    public ArrayList<Bruker> finnBruker(String soeketekst){
        if(soeketekst == null){
            return null;
        }
        JdbcTemplate con = new JdbcTemplate(dataKilde);

        return null; //TODO FInnish method
    }
}
