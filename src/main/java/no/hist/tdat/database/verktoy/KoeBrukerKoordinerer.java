package no.hist.tdat.database.verktoy;

import no.hist.tdat.javabeans.Oving;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Olve André on 20.01.14.
 */
public class KoeBrukerKoordinerer implements RowMapper<KoeBruker> {
    @Override
    public KoeBruker mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        KoeBruker koeBruker = new KoeBruker();

        koeBruker.setKoe_id(resultSet.getInt("koe_id"));
        koeBruker.setMail(resultSet.getString("mail"));
        koeBruker.setKoe_plass(resultSet.getInt("koe_plass"));
        koeBruker.setOvingsnr(resultSet.getString("ovingsnummer"));
        koeBruker.setBeskrivelse(resultSet.getString("info"));
        return koeBruker;
    }
}
