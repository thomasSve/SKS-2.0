package no.hist.tdat.kontrollere;

/**
 * Created by Thomas on 14.01.14.
 */

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class EndrePassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "skiftPassord.htm")
    private String endrePassordet(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result) {

        if (result.hasErrors()) {
            return "endrePassord.htm";
        }
      /*  if ((bruker.getGammeltPassord().equals(sessionBruker.getPassord()))
                && bruker.getNyttPassord().equals(bruker.getBekreftPassord())) {
            if (bruker.getNyttPassord().length() > 5) {
                service.hentInnlogget().setPassord(bruker.getNyttPassord());
            }
        }
        Bruker brook = service.endrePassord(bruker, nPassord);
        if (brook == null) {
            return "endrePassord.htm";
        }*/
        return "endrePassord.htm";
    }
}
