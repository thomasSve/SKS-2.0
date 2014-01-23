package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.utils.PassordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * @author Thomas
 */
@Controller
public class EndrePassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "skiftPassord.htm")
    private String endrePassordet(@Valid @ModelAttribute("passord") PassordBeans passord, BindingResult result, HttpServletRequest request, HttpSession session, Model modell) {

        if (result.hasErrors()) {
            modell.addAttribute("melding", "");
            return "endrePassord";
        }

        String gammeltP = request.getParameter("gammeltPassord");
        String bekreftPassord = request.getParameter("bekreftPassord");
        String nyttPassord = request.getParameter("nyttPassord");
        System.out.println("Gammelt: " + gammeltP + ", Bekreft: " + bekreftPassord + ", Nytt: " + nyttPassord);
        Bruker bruker = (Bruker)session.getAttribute("innloggetBruker");
        if(nyttPassord.length() > 4){
            if ((nyttPassord.equals(bekreftPassord))) {
                if (PassordService.sammenliknPassord(gammeltP, bruker)) {
                    bruker.setPassord(nyttPassord);
                    if(service.endrePassord(bruker.getMail(), bruker.getPassord())){
                        modell.addAttribute("vellykket", "Vellykket");
                        return "endrePassord";
                    }
                    modell.addAttribute("melding", "Feil ved Database");
                    return "endrePassord";

                }
                modell.addAttribute("melding", "Du skreiv inn feil passord!");
                return "endrePassord";
            }
            modell.addAttribute("melding", "Feil ved bekrefting av Passord!");
            return "endrePassord";
        }
        modell.addAttribute("melding", "For kort nytt Passord, må være minst 5 karakterer lang!");
        return "endrePassord";
    }
}
