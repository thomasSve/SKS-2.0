package no.hist.tdat.kontrollere;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.koe.Koe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import no.hist.tdat.javabeans.Bruker;

/**
 * Created by Roger Foss
 */
@Controller
public class EndreStudentKontroller {

    @RequestMapping(value="leggTilStudentListe")
    public String leggTilListe(Model model, HttpServletRequest request){

        String soketekst = request.getParameter("soketekst");
        PersonerBeans personerBeans = new PersonerBeans();

        Bruker valgtBruker = personerBeans.finnStudent(soketekst);

        model.addAttribute("personerBeans", valgtBruker);

        return "endreStudent";
    }
}
