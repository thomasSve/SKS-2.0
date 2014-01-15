package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class FinnBrukerKontroller {

    @Autowired
    BrukerService service;

    @RequestMapping("/search.htm")
    public String finnBruker(@ModelAttribute("personerBeans") PersonerBeans personerBeans,@ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request){

        String brukere = request.getParameter("srch-term");
        personerBeans.setValgt(service.finnBruker(brukere));
        modell.addAttribute("personerBeans", personerBeans);


        return "adminBrukere";
    }



}
