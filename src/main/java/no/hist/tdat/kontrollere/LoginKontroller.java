package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "loggerinn.SSL")
public class LoginKontroller {

    @RequestMapping(value = "$_POST[]")
    private String loggerInn(@Valid @ModelAttribute Bruker bruker) {
        return "/";
    }


}
