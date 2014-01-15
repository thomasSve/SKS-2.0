package no.hist.tdat.kontrollere;

import com.mysql.jdbc.StringUtils;
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value="/listeBrukerRediger.htm", method = RequestMethod.POST)
    public String finnBruker(@ModelAttribute("bruker") Bruker bruker,Model modell,@ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request){
        String mail = request.getParameter("brukerIndex");
        service.slettBruker(mail.trim());
        modell.addAttribute("personerBeans", personerBeans);
        return "/search.htm";
    }
}

