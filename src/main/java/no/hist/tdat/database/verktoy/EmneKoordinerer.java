package no.hist.tdat.database.verktoy;


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
public class EmneKoordinerer implements RowMapper<Emne>{
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public Emne mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Emne emne = new Emne();
        emne.setForeleser(resultSet.getInt("foreleser"));
        emne.setEmneKode(resultSet.getString("emnekode"));
        emne.setEmneNavn(resultSet.getString("emnenavn"));//TODO legg til øvinger
        return emne;
    }
}
