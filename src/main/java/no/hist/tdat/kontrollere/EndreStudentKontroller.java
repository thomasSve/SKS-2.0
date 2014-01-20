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
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request, HttpSession session) {
        String txt = request.getParameter("soketekst");
        if (txt == null || txt.equals("")) {
            return "endreStudent";
        }
        personerBeans.setValgt(service.finnStudenter(txt));
        session.setAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }


    @RequestMapping(value="endreValgtBruker", method = RequestMethod.POST)
    public String endreValgtBruker(HttpServletRequest request, HttpSession session){
        //PersonerBeans personerBeans = (PersonerBeans) session.getAttribute("personerBeans");
        String mail = request.getParameter("brukerIndex");
        Bruker b = service.hentBruker(mail);
        b.setEmne(service2.hentEmnerForStud(b.getMail()));

        session.setAttribute("valgtPerson", b);
        return "endreValgtStudent";
    }

    @RequestMapping(value = "videresend")
    public String videresend(HttpServletRequest request) {
        String tilb = request.getParameter("tilbake");
        if (tilb != null) {
            return "endreStudent";
        }
        return "endreValgtStudent";
    }

        //ferdig, men ikke testet
    @RequestMapping(value = "gjorTilStudass")
    public String settStudass(HttpServletRequest request, HttpSession session) {
        String lagre = request.getParameter("lagre");

        if (lagre != null) {
            Bruker b = (Bruker) session.getAttribute("valgtPerson");
            //service.settStudass("EMNEKODE","DELEMNE",b.getMail()); //kan legges i if, for å få tilbakemeld, bruker boolean
            return "endreValgtStudent";
        }
        return "endreStudent";
    }

        //ferdig, men ikke testet
    @RequestMapping(value = "leggTilFag")
    public String leggTilFag(HttpServletRequest request, HttpSession session) {
        String lagre = request.getParameter("lagre");

        if (lagre != null) {
            //Bruker b = personerBeans.getValgtBruker();
            Bruker b = (Bruker) session.getAttribute("valgtPerson");
            //service.leggTilEmne("EMNEKODE",b.getMail(), 0); //kan legges i if, for å få tilbakemeld, bruker boolean
            return "endreValgtStudent";
        }
        return "endreStudent";
    }

        //ferdig, men ikke testet
    @RequestMapping(value = "fjernFag")
    public String fjernFag(HttpServletRequest request, HttpSession session) {
        String lagre = request.getParameter("lagre");

        if (lagre != null) {
            Bruker b = (Bruker) session.getAttribute("valgtPerson");
            //service.fjernEmne("EMNEKODE",b.getMail()); //kan legges i if, for å få tilbakemeld, bruker boolean
            return "endreValgtStudent";
        }
        return "endreStudent";
    }
}


