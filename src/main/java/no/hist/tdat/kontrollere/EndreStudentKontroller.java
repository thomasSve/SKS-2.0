package no.hist.tdat.kontrollere;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Emner;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.koe.Koe;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BrukerService service;

    @RequestMapping(value="leggTilStudentListe")
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans,@ModelAttribute("bruker") Bruker bruker,Model modell, HttpServletRequest request){
        String brukere = request.getParameter("soketekst");
        personerBeans.leggTil(service.hentBruker(brukere));
        modell.addAttribute("personerBeans", personerBeans);
        return "endreStudent";

/**
        if (personerBeans.getValgt() == null || personerBeans.getValgt().size() == 0) {
            personerBeans = new PersonerBeans();
        }
        String soketekst = request.getParameter("soketekst");
        Bruker valgtBruker = personerBeans.finnStudent(soketekst);

        personerBeans.leggTil(valgtBruker);
        model.addAttribute("personerBeans", personerBeans);

        return "endreStudent";
 */
    }

    @RequestMapping(value="fjernStudent")
    public String fjernStudent(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request) {
        /**
        int radNr = -1;
        for (Integer i = 0; i < personerBeans.getValgt().size(); i++) {

            String knappNrVar = request.getParameter(i.toString());
              if (knappNrVar != null && !knappNrVar.equals("")) {
                radNr = Integer.parseInt(knappNrVar);
                break;
            }
        }
         */
        System.out.println(request.getParameter("index"));
        String index = request.getParameter("index");
        Integer indexen = Integer.parseInt(index);

        service.slettBruker(personerBeans.getValgt().get(indexen));
        model.addAttribute("personerBeans", personerBeans);

        return "endreStudent";
    }

    @RequestMapping(value="bekreftelse")
    public String bekreftelse(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request) {
        String opersasjon = request.getParameter("opValg");
        ArrayList<Emner> fellesEmner;

        if (!opersasjon.equals("leggInnFag")) {
            fellesEmner = personerBeans.finnFellesEmner();
        }



        //TODO
        return "endreStudent";
    }


}
