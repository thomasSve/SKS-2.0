package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;


@Controller
public class LeggTilBrukerKontroller {

    @Autowired
    BrukerService service;

    /**
     * Kontroller for å legge til bruker i databasen
     * @param bruker
     * @param result
     * @return adminBrukere sin side, med feilmeldinger om
     */
    @RequestMapping("/leggTilBruker.htm")
    public String leggTilBruker(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result) {
        if(result.hasErrors()){
            return "adminBrukere";
        }else{
            if(service.leggTilBruker(bruker)){
                return "adminBrukere";
            }else{
                return "adminBrukere";
            }
        }
    }
}
