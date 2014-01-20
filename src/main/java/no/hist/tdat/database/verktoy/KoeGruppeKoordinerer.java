package no.hist.tdat.database.verktoy;


import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.KoeGrupper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klassen BrukerKoordinerer tar i mot data fra databaser, slik at spørringer retunerer
 * objekter av typen Bruker.
 *
 * @author vimCnett
 */
public class KoeGruppeKoordinerer implements RowMapper<KoeGrupper>{
    /**
     * Denne metoden er en mal på hvordan Bruker objekter skal opprettes ved henting fra database.
     * @param resultSet settes av Spring, og er resultatet fra database-spørringen
     * @param rowNum sier hvor langt i resultsettet vi har kommet
     * @return et Bruker-objekt ut i fra datane i databasen.
     * @throws SQLException exception blir tatt hånd om av rammeverket.
     */
    @Override
    public KoeGrupper mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        KoeGrupper koeGrupper = new KoeGrupper();
        koeGrupper.setSitteplass(resultSet.getString("plassering_navn"));
        koeGrupper.setBordnr(resultSet.getInt("bordnummer"));
        koeGrupper.setKommentar(resultSet.getString("info"));
        koeGrupper.setKlokkeslett(resultSet.getDate("tidspunkt"));
        koeGrupper.setKoePlassering(resultSet.getInt("koe_plass"));
        //TODO legg til lederobjekt, medlemmer og øvinger i databaseconnectorMetode.
        return koeGrupper;
    }
}
