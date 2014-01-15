package no.hist.tdat.kontrollere;

/**
 * Created by Thomas on 14.01.14.
 */

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class EndrePassordKontroller {
    @Autowired
    BrukerService service;

    @RequestMapping(value = "skiftPassord.htm")
    private String endrePassordet(@Valid @ModelAttribute("passord") PassordBeans passord, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "endrePassord";
        }

        String gammeltP = request.getParameter("gammeltPassord");
        String bekreftPassord = request.getParameter("bekreftPassord");
        String nyttPassord = request.getParameter("nyttPassord");
        System.out.println("Gammelt: " + gammeltP + ", Bekreft: " + bekreftPassord + ", Nytt: " + nyttPassord);
        java.util.Enumeration<String> sessEnum = request.getSession().getAttributeNames();
        Bruker denne = new Bruker();
        while (sessEnum.hasMoreElements()) {
            String s = sessEnum.nextElement();
            denne = (Bruker) request.getSession().getAttribute(s);
        }


        if ((nyttPassord.equals(bekreftPassord)) && nyttPassord.length() > 4) {
            if (denne.sammenliknPassord(gammeltP)) {
                if(service.endrePassord(denne.getMail(), nyttPassord)){
                    System.out.println("Vellykket");
                    return "endrePassord";
                }
                System.out.println("Feil ved Database");
                return "endrePassord";

            }
            System.out.println("Feil ved sammenlikning av gamle");
            return "endrePassord";
        }
        System.out.println("Feil ved bekrefting av Passord.");
        return "endrePassord";
    }
}
