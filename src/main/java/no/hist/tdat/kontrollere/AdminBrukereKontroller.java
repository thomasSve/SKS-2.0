package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

/**
 * Created by Eirik on 13.01.14.
 */
@Controller
public class AdminBrukereKontroller {

    @Autowired
    BrukerService service;

    /**
     * Kontroller for å legge til bruker i databasen
     *
     * @param
     * @return adminBrukere sin side, med feilmeldinger om
     */

    @RequestMapping(value = "leggTilFil.htm", method = RequestMethod.POST)
    public String leggTilFil(@ModelAttribute Bruker bruker, HttpServletRequest req,Model modell) {
        String tab = req.getParameter("tab");
        String text = req.getParameter("newText");
        String[] linje = text.split("\\r?\\n");
        String epost, fnavn, enavn = "";
        try {
            int teller = 0;
            while (teller < linje.length) {
                try {
                    String[] po = linje[teller].split(", ");
                    epost = po[0].toLowerCase().trim();
                    fnavn = po[1];
                    enavn = po[2];
                    service.leggTilBruker(new Bruker(epost, Bruker.STUDENT_RETTIGHET, fnavn, enavn));
                } catch (org.springframework.dao.DuplicateKeyException t) {
                    teller++;
                }
                teller++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            modell.addAttribute("tabForm", tab);
            modell.addAttribute("filMelding", "Feil under registrering av brukere");
            return "adminBrukere";
        }
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("filMelding", "Brukere registrert");
        return "adminBrukere";
    }

    /**
     * Søkemetode i adminBrukere
     * @param personerBeans hjelpeklasse
     * @param bruker bruker objekt
     * @param modell sende objekt vidre
     * @param request henter objekt
     * @param session lagrer objekt i session
     * @return wiew adminBrukere
     */
    @RequestMapping("/search.htm")
    public String finnBruker(@ModelAttribute("personerBeans") PersonerBeans personerBeans, @ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request,HttpSession session) {
        String tab = request.getParameter("tab");
        String brukere = request.getParameter("srch-term");
        if(brukere == null){
            if(session.getAttribute("sok")!=null){
                personerBeans.setValgt(service.finnBruker((String)session.getAttribute("sok")));
                session.removeAttribute("sok");
            }else{
                personerBeans.setValgt(service.finnBruker(brukere));
                session.setAttribute("sok", brukere);
            }
        }else{
            personerBeans.setValgt(service.finnBruker(brukere));
        }
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("personerBeans", personerBeans);
        return "adminBrukere";
    }

    /**
     * Kontroller for å legge til bruker i databasen
     *
     * @param bruker
     * @param result
     * @return adminBrukere sin side, med feilmeldinger om
     */
    @RequestMapping("/leggTilBruker.htm")
    public String leggTilBruker(@Valid @ModelAttribute("bruker") Bruker bruker, BindingResult result, Model modell, HttpServletRequest request) {
        String tab = request.getParameter("tab");
        if (result.hasErrors()) {
            modell.addAttribute("melding","FEIL: Fyll inn alle feltene");
            modell.addAttribute("tabForm", tab);
            return "adminBrukere";
        }else if(bruker.getEtternavn().trim().length()<2 || bruker.getEtternavn().trim().length()<2){
            modell.addAttribute("melding","FEIL: Fornavn og/eller etternavn er for kort");
            modell.addAttribute("tabForm", tab);
            return "adminBrukere";
        }else{
            try {
                if (service.leggTilBruker(bruker)) {
                    modell.addAttribute("melding","REGISTRERT: Bruker " + bruker.getMail());
                    modell.addAttribute("tabForm", tab);
                    return "adminBrukere";
                } else {
                    modell.addAttribute("melding","FEIL: Fyll inn alle feltene");
                    return "adminBrukere";
                }

            } catch (org.springframework.dao.DuplicateKeyException e) {
                modell.addAttribute("melding","FEIL: e-postadresse <" + bruker.getMail() + "> finnes fra før");
                modell.addAttribute("tabForm", tab);
                return "adminBrukere";
            }
        }
    }


    /**
     * Kontroller for å slette en bruker ifra databasen
     *
     * @param modell
     * @param personerBeans
     * @param request
     * @return til siden search.htm
     */
    @RequestMapping(value = "/slettBruker.htm", method = RequestMethod.POST)
    public String slettBruker(Model modell, @ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request) {
        String tab = request.getParameter("tab");
        String mail = request.getParameter("brukerIndex");
        service.slettBruker(mail.trim());
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("personerBeans", personerBeans);
        return "/adminBrukere.htm";
    }


   @RequestMapping(value = "/redigerBruker.htm", method = RequestMethod.POST )
    public String redigerBruker(@ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String mail = request.getParameter("brukerIndex");
        Bruker redigerBrukere = service.hentBruker(mail);
        session.removeAttribute("redigerBrukere");
        session.setAttribute("redigerBrukere", redigerBrukere);
        if(redigerBrukere==null){
            modell.addAttribute("melding", "Finner ikke bruker i databasen");
            return "adminBrukereEndre";
        }else{
            return "adminBrukereEndre";
        }
    }

    @RequestMapping(value = "/redigerBrukerLagre.htm", method = RequestMethod.POST )
    public String redigerBrukerLagre(@ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String mail = request.getParameter("brukerIndex");
        Bruker redigerBrukere = service.e(mail);
        session.removeAttribute("redigerBrukere");
        session.setAttribute("redigerBrukere", redigerBrukere);
        if(redigerBrukere==null){
            modell.addAttribute("melding", "Finner ikke bruker i databasen");
            return "search.htm";
        }else{
            return "search.htm";
        }
    }


}

