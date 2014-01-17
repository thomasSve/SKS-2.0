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
import javax.servlet.http.HttpSession;
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
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model modell, HttpServletRequest request, HttpSession session) {
        String txt = request.getParameter("soketekst");
        if (txt == null || txt.equals("")) {
            return "endreStudent";
        }
        personerBeans.setValgt(service.finnStudenter(txt));
        session.setAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }


    @RequestMapping(value="endreValgtBruker", method = RequestMethod.POST)
    public String ananasbiter(Model modell, HttpServletRequest request, HttpSession session){
        PersonerBeans personerBeans = (PersonerBeans) session.getAttribute("personerBeans");
        String mail = request.getParameter("brukerIndex");
        Bruker b = service.hentBruker(mail);
        b.setEmne(service2.hentEmnerForStud(b.getMail()));

        personerBeans.setValgtBruker(b);
        session.setAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }

        //ikke begynt
    @RequestMapping(value = "gjorTilStudass")
    public String studass(Model modell, HttpServletRequest request, HttpSession session) {
        PersonerBeans personerBeans = (PersonerBeans) session.getAttribute("personerBeans");
        Bruker b = personerBeans.getValgtBruker();
        System.out.println(personerBeans);
        for (int i = 0; i < b.getEmne().size(); i++) {
            System.out.println(b.getEmne().get(i).getEmneNavn());
        }

        return "endreStudent";
    }

    @RequestMapping(value = "leggTilFag")
    public String leggTilFag(Model modell, HttpServletRequest request, HttpSession session) {
        PersonerBeans personerBeans = (PersonerBeans) session.getAttribute("personerBeans");
        Bruker b = personerBeans.getValgtBruker();
        System.out.println(personerBeans.getValgtBruker().getFornavn()+" "+personerBeans.getValgtBruker().getEtternavn());

        service.leggTilEmne("EMNEKODE",b.getMail(), 0); //kan legges i if, for å få tilbakemeld, bruker boolean

        return "endreStudent";
    }

    @RequestMapping(value = "fjernFag")
    public String fjernFag(Model modell, HttpServletRequest request, HttpSession session) {
        PersonerBeans personerBeans = (PersonerBeans) session.getAttribute("personerBeans");
        Bruker b = personerBeans.getValgtBruker();
        System.out.println(personerBeans);
        for (int i = 0; i < b.getEmne().size(); i++) {
            System.out.println(b.getEmne().get(i).getEmneNavn());
        }

        return "endreStudent";
    }
}


