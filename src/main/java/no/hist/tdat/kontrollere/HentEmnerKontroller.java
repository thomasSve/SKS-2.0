package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by ted on 1/14/14.
 */
@Controller
public class HentEmnerKontroller {

    @Autowired
    EmneService service;
/*
    @RequestMapping("/minside.htm")
    public String hentEmner(@Valid @ModelAttribute("bruker") Bruker bruker, HttpSession session) {
        {
            if(service.leggTilBruker(bruker)){
                return "adminBrukere";
            }else{
                return "adminBrukere";
            }
        }
    }
}*/


}
