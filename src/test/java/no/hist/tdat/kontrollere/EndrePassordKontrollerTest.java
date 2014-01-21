package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.samples.context.WebContextLoader;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

/**
 * @author Jørgen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class EndrePassordKontrollerTest {

    @Resource
    /* Accurate spring-mvc-dispatcher-servlet */
    private WebApplicationContext context;
    /* Mock MVC-dispatcher-servlet */
    private MockMvc mockMvc;
    /* Dummy form data */
    private String old_password = "yoloSwag";
    private String new_password = "thisNewPasswodes";
    private String new_duplicate_password = "thisNewPasswodes";
    private Bruker bruker;
    private String empty = "";
    private String shortPw = "erg";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(context).build();
        bruker = new Bruker("stavrum@nte.no", 1, "Pål", "Stavrum");
        bruker.setPassord(old_password);
    }

    @Test
    public void testEndrePassordetOK() throws Exception {
        mockMvc.perform(post("/skiftPassord.htm")

                .sessionAttr("innloggetBruker", bruker)
                .param("gammeltPassord", old_password)
                .param("bekreftPassord", new_password)
                .param("nyttPassord", new_duplicate_password)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("endrePassord"))
                .andExpect(model().attribute("melding", is("Vellykket")));
    }

    @Test
    public void testEndrePassordUlikePassord() throws Exception {
        mockMvc.perform(post("/skiftPassord.htm")
                .sessionAttr("innloggetBruker", bruker)
                .param("gammeltPassord", old_password)
                .param("bekreftPassord", old_password)
                .param("nyttPassord", new_duplicate_password)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("endrePassord"))
                .andExpect(model().attribute("melding", is("Feil ved bekrefting av Passord!")));
    }

    @Test
    public void testEndrePassordFeilGammeltPassord() throws Exception {
        mockMvc.perform(post("/skiftPassord.htm")
                .sessionAttr("innloggetBruker", bruker)
                .param("gammeltPassord", new_password)
                .param("bekreftPassord", new_password)
                .param("nyttPassord", new_duplicate_password)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("endrePassord"))
                .andExpect(model().attribute("melding", is("Du skreiv inn feil passord!")));
    }

    @Test
    public void testEndrePassordFeilISkjema() throws Exception {
        mockMvc.perform(post("/skiftPassord.htm")
                .sessionAttr("innloggetBruker", bruker)
                .param("gammeltPassord", empty)
                .param("bekreftPassord", new_password)
                .param("nyttPassord", new_duplicate_password)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("endrePassord"))
                .andExpect(model().attribute("melding", is(empty)));
    }

    @Test
    public void testEndrePassordForKort() throws Exception {
        mockMvc.perform(post("/skiftPassord.htm")
                .sessionAttr("innloggetBruker", bruker)
                .param("gammeltPassord", old_password)
                .param("bekreftPassord", shortPw)
                .param("nyttPassord", shortPw)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("endrePassord"))
                .andExpect(model().attribute("melding", is("For kort nytt Passord, må være minst 5 karakterer lang!")));
    }

}
