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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class GlemtPassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "sendNyttPassord")
    private String endrePassordet(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()){
            return "glemtPassord.htm";
        }
        String mail = request.getParameter("mail");
        System.out.println("Mail: " + mail);

        if (service.hentBruker(mail)!=null) {

        }
        return "glemtPassord.htm";
    }
}
