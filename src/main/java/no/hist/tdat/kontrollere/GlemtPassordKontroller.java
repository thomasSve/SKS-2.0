package no.hist.tdat.kontrollere;

/**
 * Created by Thomas on 14.01.14.
 */
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class GlemtPassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "sendNyttPassord")
    private String endrePassordet(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result) {

        if(result.hasErrors()){
            return "glemtPassord.htm";
        }

        Bruker brook = service.hentBruker(bruker.getMail());
        if(brook!=null){
            String nPassord = brook.genererPassord();
            brook.setPassord(nPassord);

            return "glemtPassord.htm";
        }
        return "glemtPassord.htm";
    }
}
