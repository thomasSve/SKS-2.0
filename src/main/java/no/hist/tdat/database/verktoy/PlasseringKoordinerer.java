package no.hist.tdat.database.verktoy;

import no.hist.tdat.javabeans.Plassering;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Henriette on 15/01/14.
 */
public class PlasseringKoordinerer implements RowMapper<Plassering> {
/**
 * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
 *
 * @param resultSet settes av Spring, og er resultatet fra database-spørringen
 * @param rowNum sier hvor langt i resultsettet vi har kommet
 * @return et Bruker-objekt ut i fra datane i databasen.
 * @throws java.sql.SQLException exception blir tatt hånd om av rammeverket.
 */
    @Override
    public Plassering mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Plassering plass = new Plassering();
        plass.setPlassering_navn(resultSet.getString("plassering_navn"));
        plass.setAnt_bord(resultSet.getInt("ant_bord"));
        return plass;
        }
        }