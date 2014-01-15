package no.hist.tdat.database;

import org.apache.tiles.request.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.server.result.samples.context.WebContextLoader;

/**
 * @author vimCnett
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class DatabaseConnectorTest {
    private MockMvc mockMvc;

    @Autowired
    DatabaseConnector databaseConnector;

    @Autowired
    WebApplicationContext context;

    @Test
    public void testLoggInn() throws Exception {

    }
}
