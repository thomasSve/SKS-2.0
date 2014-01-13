package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class LoginKontroller {

    @RequestMapping(value = "loggerinn.SSL")
    private String loggerInn(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result) {

        if(result.hasErrors()){
            return "loggInn";
        }
        bruker=bruker.loggInn();
        if(bruker==null){
            return "/login.htm";
        }
        return "/minside.htm";
    }
}
