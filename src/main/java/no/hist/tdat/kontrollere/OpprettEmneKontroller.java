package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.DelEmne;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Eirik on 20.01.14.
 */

@Controller
public class OpprettEmneKontroller {
    @Autowired
    EmneService eservice;
    @Autowired
    BrukerService bservice;
    @RequestMapping("lagEmne")
    public String emne(@ModelAttribute(value = "emne") Emne emne, @ModelAttribute("delemne")DelEmne delEmne, Model model, HttpSession session) {
        System.out.println("inne i lag emne");
        try{
            eservice.opprettEmne(emne);
            model.addAttribute("emnerett", "Emne \""+emne.getEmneNavn()+"\" med emnekode \""+emne.getEmneKode()+"\" er opprettet");
            session.setAttribute("emne", emne);
            return "opprettDelemne";
        }catch(org.springframework.dao.DuplicateKeyException e){
            model.addAttribute("emneSQLfeil", "Emnenavn eller emnekode er opprettet fra f&oslash;r");
            return "opprettEmne";
        }
    }
    @RequestMapping(value="sokBoks", method= RequestMethod.POST)
    public String finnL(@ModelAttribute(value = "emne") Emne emne, @ModelAttribute("personerBeans") PersonerBeans personerBeans, @ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request,HttpSession session) {
        String tab = request.getParameter("tab");
        String brukere = request.getParameter("srch-term");
        if(brukere == null){
            if(session.getAttribute("sok")!=null){
                personerBeans.setValgt(bservice.finnL((String)session.getAttribute("sok")));
                session.setAttribute("sok", (String)session.getAttribute("sok"));
            }
        }else{
            personerBeans.setValgt(bservice.finnL(brukere));
            session.setAttribute("sok", brukere);
        }
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("personerBeans", personerBeans);
        return "opprettEmne";
    }

    @RequestMapping(value = "/velgBruker.htm", method = RequestMethod.POST)
    public String velgBruker(@ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String mail = request.getParameter("brukerIndex");
        Bruker redigerBrukere = bservice.hentBruker(mail);
        session.setAttribute("redigerBrukere", redigerBrukere);
        if (redigerBrukere == null) {
            modell.addAttribute("melding", "Finner ikke bruker i databasen");
            return "adminBrukereEndre";
        } else {
            return "adminBrukereEndre";
        }

    }
}
