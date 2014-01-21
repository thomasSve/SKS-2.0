package no.hist.tdat.kontrollere;

        import no.hist.tdat.database.DatabaseConnector;
        import no.hist.tdat.javabeans.Bruker;
        import no.hist.tdat.javabeans.beanservice.EmneService;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
        import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
        import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import org.springframework.test.web.server.MockMvc;
        import org.springframework.test.web.server.MockMvcBuilder;
        import org.springframework.test.web.server.samples.context.WebContextLoader;
        import org.springframework.test.web.server.setup.MockMvcBuilders;
        import org.springframework.web.context.WebApplicationContext;

        import javax.annotation.Resource;
        import javax.sql.DataSource;

        import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
        import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
        import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

/**
 * @author Jørgen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class NavigasjonsKontrollerTest {

    @Resource
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    DataSource dataSource;

    @Autowired
    EmneService emneService;

    private Bruker innloggetBruker;
    private String email = "ob@mail.com";
    private String password = "xxx";


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(context).build();
        DatabaseConnector con = new DatabaseConnector();
        con.setDataKilde(dataSource);
        innloggetBruker = con.loggInn(new Bruker(email, password));
        emneService.hentEmner(innloggetBruker);
        System.out.println(innloggetBruker);
    }

    @Test
    public void testKoOversikt() throws Exception{
        mockMvc.perform(post("/koOversikt.htm")
                .param("hiddenKoe", "0")
                .param("hiddenEmneNavn", "2")
                .sessionAttr("innloggetBruker", innloggetBruker))
        .andExpect(status().isOk())
        .andExpect(view().name("koOversikt"));
    }





}
