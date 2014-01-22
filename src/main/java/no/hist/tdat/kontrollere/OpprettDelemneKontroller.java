package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eirik on 20.01.14.
 */

@Controller
public class OpprettDelemneKontroller {
    @Autowired
    EmneService service;
    @RequestMapping("/setKrav.htm")
    public String delemne(@ModelAttribute(value = "delemne")DelEmne delEmne, BindingResult result, Model model) {
        try{
            //service.leggTilEmne(emne);
            //model.addAttribute("emnerett", "Emne \""+emne.getEmneNavn()+"\" med emnekode \""+emne.getEmneKode()+"\" er opprettet");
            return "opprettDelemne";
        }catch(org.springframework.dao.DuplicateKeyException e){
            model.addAttribute("emneSQLfeil", "Emnenavn eller emnekode er opprettet fra f&oslash;r");
            return "opprettEmne";
        }
    }
}
