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
import javax.validation.Valid;

/**
 * Tar av seg kontrollerer adminoppgaver i fra viewene.
 *
 * @author  Eirik, Henriette, Geir Morten
 */
@Controller
public class AdminBrukereKontroller {

    @Autowired
    private BrukerService service;

    /**
     * Kontroller for å legge til bruker i databasen
     *
     * @param bruker et brukerobjekt //TODO ikke nødvendig her.
     * @param req http-request form data. Inneholder fil-teksten
     * @param modell modellen som data sendes tilbake til klienten med.
     * @return adminBrukere sin side, med feilmeldinger om
     *
     * @author Henriette; Eirik
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
            return "adminBrukere";
        }
        modell.addAttribute("tabForm", tab);
        return "adminBrukere";
    }

    /**
     * Sender en liste med brukere tilbake til viewet.
     *
     * @param personerBeans tar med beans
     * @param bruker //TODO vet ikke om det er nødvendig her.
     * @param modell objektet lista sendes til
     * @param request forespørselobjektet.
     * @return view-navn til det viewet som skal vises
     *
     * @author //TODO legg til hvem som er maintainer.
     */
    @RequestMapping("/search.htm")
    public String finnBruker(@ModelAttribute("personerBeans") PersonerBeans personerBeans, @ModelAttribute("bruker") Bruker bruker, Model modell, HttpServletRequest request) {
        String tab = request.getParameter("tab");
        String brukere = request.getParameter("srch-term");
        personerBeans.setValgt(service.finnBruker(brukere));
        modell.addAttribute("sok", brukere);
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
    @RequestMapping(value = "/listeBrukerRediger.htm", method = RequestMethod.POST)
    public String slettBruker(Model modell, @ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request) {
        String tab = request.getParameter("tab");
        String mail = request.getParameter("brukerIndex");
        service.slettBruker(mail.trim());
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("personerBeans", personerBeans);
        return "/search.htm";
    }
}

