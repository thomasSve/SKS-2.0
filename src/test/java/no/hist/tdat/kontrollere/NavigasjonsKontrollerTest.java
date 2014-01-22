package no.hist.tdat.kontrollere;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.samples.context.WebContextLoader;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

/**
 * @author Jørgen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class NavigasjonsKontrollerTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    EmneService emneService;
    @Resource
    private WebApplicationContext context;
    private MockMvc mockMvc;
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
    public void testKoOversikt() throws Exception {
        mockMvc.perform(post("/koOversikt.htm")
                .param("hiddenKoe", "0")
                .param("hiddenEmneNavn", "2")
                .sessionAttr("innloggetBruker", innloggetBruker))
                .andExpect(status().isOk())
                .andExpect(view().name("koOversikt"));
    }

    @Test
    public void omdirigerErrorTest() throws Exception {
        mockMvc.perform(get("/svahili"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void omdirEndreStudentTest() throws Exception {
        mockMvc.perform(get("/endreStudent.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("endreStudent"));
    }

    @Test
    public void omdirigerMinsideTest() throws  Exception {
        mockMvc.perform(get("/minside.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("minside"));
    }

    @Test
    public void hentMittEmneTest() throws Exception {
        mockMvc.perform(get("/emne.htm")
                .sessionAttr("innloggetBruker", innloggetBruker))
                .andExpect(status().isOk())
                .andExpect(view().name("minside"));
    }

    @Test
    public void ovingsOppleggTest() throws Exception {
        mockMvc.perform(get("/ovingsOpplegg.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("ovingsOpplegg"));
    }

    @Test
    public void nyStudentTest() throws Exception {
        mockMvc.perform(get("/nyStudent.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("nyStudent"));
    }

    @Test
    public void loggUt() throws Exception {
        mockMvc.perform(get("/loggUt.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("loggInn"));
    }

    @Test
    public void opprettEmne() throws Exception {
        mockMvc.perform(get("/opprettEmne.htm"))
                .andExpect(status().isOk())
                .andExpect(view().name("opprettEmne"));
    }


}
