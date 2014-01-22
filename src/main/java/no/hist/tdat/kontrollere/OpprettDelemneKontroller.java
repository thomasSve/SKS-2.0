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
public class OpprettDelemneKontroller {
    @Autowired
    EmneService service;
    @RequestMapping("lagDelemne")
    public String delemne(@ModelAttribute(value = "delemne")DelEmne delEmne, Model model, HttpSession session) {
        try{
            Emne emne = (Emne)session.getAttribute("emne");
            service.opprettDelemne(delEmne, emne);
            model.addAttribute("delemnerett", "Delemne \""+delEmne.getDelEmneNavn()+"\" med delemnekode \""+delEmne.getNr()+"\" er opprettet");
            return "ovingsopplegget";
        }catch(org.springframework.dao.DuplicateKeyException e){
            model.addAttribute("delemneSQLfeil", "Delemnenavn eller delemnekode er opprettet fra f&oslash;r");
            return "opprettDelemne";
        }
    }
}
