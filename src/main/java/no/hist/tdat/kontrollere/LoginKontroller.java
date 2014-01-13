package no.hist.tdat.kontrollere;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by ted on 1/10/14.
 */
@Controller
@RequestMapping(value="loggerinn.SSL")
public class LoginKontroller {

    @RequestMapping(value="$_POST[]")
    private String loggerInn(@Valid @ModelAttribute Bruker bruker){
        DatabaseConnector db = new DatabaseConnector();



        return "/";
    }


}
