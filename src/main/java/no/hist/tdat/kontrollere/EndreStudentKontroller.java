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
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request){

        if (personerBeans.getValgt() == null || personerBeans.getValgt().size() == 0) {
            personerBeans = new PersonerBeans();
        }
        String soketekst = request.getParameter("soketekst");
        Bruker valgtBruker = personerBeans.finnStudent(soketekst);

        personerBeans.leggTil(valgtBruker);
        model.addAttribute("personerBeans", personerBeans);

        return "endreStudent";
    }

    @RequestMapping(value="fjernStudent")
    public String fjernStudent(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request) {
        int radNr = -1;
        for (Integer i = 0; i < personerBeans.getValgt().size(); i++) {
            String knappNrVar = request.getParameter(i.toString());
            if (knappNrVar != null && !knappNrVar.equals("")) {
                radNr = Integer.parseInt(knappNrVar);
                break;
            }
        }
        personerBeans.fjernStudent(radNr);
        model.addAttribute("personerBeans", personerBeans);

        return "endreStudent";
    }

    @RequestMapping(value="bekreftelse")
    public String bekreftelse(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request){



        String opersasjon = request.getParameter("soketekst");
        //TODO
        return "endreStudent";
    }

}
