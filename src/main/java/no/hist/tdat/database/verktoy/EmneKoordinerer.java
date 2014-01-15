package no.hist.tdat.database.verktoy;


import no.hist.tdat.javabeans.Emner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klassen BrukerKoordinerer tar i mot data fra databaser, slik at spørringer retunerer
 * objekter av typen Bruker.
 *
 * @author vimCnett
 */
public class EmneKoordinerer implements RowMapper<Emner>{
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public Emner mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Emner emne = new Emner();
        emne.setEmneKode(resultSet.getString("emnekode"));
        emne.setEmneNavn(resultSet.getString("emnenavn"));//TODO legg til øvinger
        return emne;
    }
}
