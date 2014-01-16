package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Roger Foss
 */
@Controller
public class EndreStudentKontroller {
    @Autowired
    BrukerService service;
    @Autowired
    EmneService service2;

    @RequestMapping(value = "leggTilStudentListe")
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model modell, HttpServletRequest request) {
        String txt = request.getParameter("soketekst");
        personerBeans.setValgt(service.finnStudenter(txt));
        modell.addAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }


    @RequestMapping(value="endreValgtBruker", method = RequestMethod.POST)
    public String ananasbiter(Model modell, HttpServletRequest request){
        String mail = request.getParameter("brukerIndex");
        Bruker bruker = service.hentBruker(mail);
        //ArrayList<Bruker> b = new ArrayList<Bruker>();
        //b.add(bruker);
        System.out.println(bruker);
        //personerBeans.setValgtBruker(b);
        modell.addAttribute("valgtBruker", bruker);
        //modell.addAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }
}


