package no.hist.tdat.database.verktoy;

import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.KoeGrupper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Henriette on 20/01/14.
 */
public class KoeKoordinerer implements RowMapper<KoeGrupper> {
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws java.sql.SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public KoeGrupper mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        KoeGrupper koeGruppe = new KoeGrupper();
        koeGruppe.setBordnr(resultSet.getInt("bordnr"));
        koeGruppe.setOvinger(resultSet.getArray());

        return koeGruppe;
    }
}