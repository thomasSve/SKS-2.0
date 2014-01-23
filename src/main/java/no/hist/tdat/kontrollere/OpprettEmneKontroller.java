package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Eirik on 20.01.14.
 */

@Controller
public class OpprettEmneKontroller {
    @Autowired
    EmneService service;
    @RequestMapping("lagEmne")
    public String emne(@ModelAttribute(value = "emne") Emne emne, @ModelAttribute("delemne")DelEmne delEmne, Model model, HttpSession session) {
        try{
            service.opprettEmne(emne);
            model.addAttribute("emnerett", "Emne \""+emne.getEmneNavn()+"\" med emnekode \""+emne.getEmneKode()+"\" er opprettet");
            session.setAttribute("emne", emne);
            return "opprettDelemne";
        }catch(org.springframework.dao.DuplicateKeyException e){
            model.addAttribute("emneSQLfeil", "Emnenavn eller emnekode er opprettet fra f&oslash;r");
            return "opprettEmne";
        }
    }
}
