package no.hist.tdat.kontrollere;

/**
 * Created by Thomas on 14.01.14.
 */

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.utils.PassordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class EndrePassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "skiftPassord.htm")
    private String endrePassordet(@Valid @ModelAttribute("passord") PassordBeans passord, BindingResult result, HttpServletRequest request, HttpSession session) {

        if (result.hasErrors()) {
            return "endrePassord";
        }

        String gammeltP = request.getParameter("gammeltPassord");
        String bekreftPassord = request.getParameter("bekreftPassord");
        String nyttPassord = request.getParameter("nyttPassord");
        System.out.println("Gammelt: " + gammeltP + ", Bekreft: " + bekreftPassord + ", Nytt: " + nyttPassord);
        Bruker bruker = (Bruker)session.getAttribute("innloggetBruker");
        if ((nyttPassord.equals(bekreftPassord)) && nyttPassord.length() > 4) {
            if (PassordService.sammenliknPassord(gammeltP, bruker)) {
                bruker.setPassord(nyttPassord);
                if(service.endrePassord(bruker.getMail(), bruker.getPassord())){
                    System.out.println("Vellykket");
                    return "endrePassord";
                }
                System.out.println("Feil ved Database");
                return "endrePassord";

            }
            System.out.println("Feil ved sammenlikning av gamle");
            return "endrePassord";
        }
        System.out.println("Feil ved bekrefting av Passord. Evt. \nFor kort nytt Passord");
        return "endrePassord";
    }
}
