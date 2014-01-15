package no.hist.tdat.database.verktoy;


import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Emne;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klassen BrukerKoordinerer tar i mot data fra databaser, slik at spørringer retunerer
 * objekter av typen Bruker.
 *
 * @author vimCnett
 */
public class DelEmneKoordinerer implements RowMapper<DelEmne>{
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws java.sql.SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public DelEmne mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DelEmne delEmne = new DelEmne();
        delEmne.setEmneKode(resultSet.getString("emnekode"));
        delEmne.setEmneNavn(resultSet.getString("emnenavn"));//TODO legg til øvinger
        return delEmne;
    }
}
private int nr;
private char semester;
private int koe_id;
private Ovingsopplegg ovinger;
private boolean koe_status;
private ArrayList<Oving> studentovinger;
