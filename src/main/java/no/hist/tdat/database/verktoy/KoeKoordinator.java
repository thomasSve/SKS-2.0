package no.hist.tdat.database.verktoy;

import no.hist.tdat.javabeans.Koe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eirik on 21.01.14.
 */

public class KoeKoordinator implements RowMapper<Koe> {
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws java.sql.SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public Koe mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Koe koe = new Koe();
        koe.setKoeId(resultSet.getInt("koe_id"));
        return koe;
    }
}
