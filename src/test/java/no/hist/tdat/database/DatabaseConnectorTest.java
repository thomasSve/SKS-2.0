package no.hist.tdat.database;

import no.hist.tdat.javabeans.Bruker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * @author vimCnett
 */
@Transactional
public class DatabaseConnectorTest {
    public Bruker[] legg_til_ny_ok;
    public Bruker[] legg_til_ny_fail;
    DatabaseConnector connector = new DatabaseConnector();
    private EmbeddedDatabase db;
    private Bruker bruker;
    private String email = "js@mail.com";
    private String password = "xxx";

    @Before
    public void foerHverTest() {
        db = new EmbeddedDatabaseBuilder().addScript("db_script/schema.sql").addScript("db_script/data.sql")
                .setType(EmbeddedDatabaseType.H2).build();
        bruker = new Bruker(email, password);
        connector.setDataKilde(db);
        legg_til_ny_ok = new Bruker[]{
                new Bruker("jorgels@stud.hist.no", 1, "Jørgen Lien", "Sellæg"),
                new Bruker("geirml@stud.hist.no", 1, "Geir Morten", "Larsen"),
                new Bruker("tedszyy@stud.hist.no", 1, "Ted Johan", "Kristoffersen"),
        };
        legg_til_ny_fail = new Bruker[]{
                new Bruker("js@mail.com", 1, "Jørgen Lien", "Sellæg"),
                new Bruker("tk@mail.com", 1, "Jørgen Lien", "Sellæg"),
                new Bruker()
        };

        for (int i = 0; i < legg_til_ny_ok.length; i++) {
            Bruker bruker1 = legg_til_ny_ok[i];
            bruker1.setPassord(password);
        }
    }

    @After
    public void etterHverTest() {
        db.shutdown();
    }

    @Test
    public void testLoggInn() throws Exception {
        System.out.println(bruker);
        Bruker inne = connector.loggInn(bruker);
        System.out.println(inne);
        assertTrue(inne.getMail().equals(email));
        System.out.println(inne.toString());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testLeggTilBruker() throws Exception {

        for (int i = 0; i < legg_til_ny_ok.length; i++) {
            Bruker bruker1 = legg_til_ny_ok[i];
            assertTrue(connector.leggTilBruker(bruker1));
        }

        for (int i = 0; i < legg_til_ny_fail.length; i++) {
            Bruker bruker1 = legg_til_ny_fail[i];
            assert (connector.leggTilBruker(bruker1));
        }

    }

    @Test
    public void testSlettBruker() throws Exception {
        assertTrue(connector.slettBruker(email));
    }


}
