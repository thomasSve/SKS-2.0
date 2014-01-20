package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
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
public class AdminBrukereKontrollerTest {
    private String file_dummy = "test@test.no, Fornavn, Etternavn \n rest@test.no, Fornavn, Etternavn";
    private String mail = "js@mail.com";


    @Resource
    WebApplicationContext context;
    @Autowired
    DataSource dataSource;
    private MockMvc mockMvc;

    @Autowired
    BrukerService service;

    @Before
    public void foerHverTest() {
        deleteTestUsers();
        mockMvc = MockMvcBuilders.webApplicationContextSetup(context).build();
    }

    @Test
    public void testLeggTilFil() throws Exception {
        mockMvc.perform(post("/leggTilFil.htm").param("newText", file_dummy))
                .andExpect(status().isOk())
                .andExpect(view().name("adminBrukere"));
    }

    @Test
    public void testLeggTilEmpty() throws Exception {
        mockMvc.perform(post("/leggTilFil.htm").param("newText", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("adminBrukere"));
    }

    @After
    public void etterHverTest() {
        deleteTestUsers();
    }

    public void deleteTestUsers() {
        JdbcTemplate con = new JdbcTemplate(dataSource);
        con.update("DELETE FROM brukere WHERE mail LIKE ?", "%@test.no");
    }

    @Test
    public void testSlettBruker() throws Exception{
        mockMvc.perform(post("/slettBruker.htm").param("brukerIndex", mail))
                .andExpect(status().isOk())
                .andExpect(view().name("adminBrukere"));
    }
}
