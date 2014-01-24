package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.EmnerBeans;
import no.hist.tdat.javabeans.beanservice.EmneService;
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

/**
 * Created by Thomas on 23.01.14.
 */

/**
 * TODO Thomas: Endre slik at faglærer kun kan søke og endre på fag som den personen er foreleser i. Admin kan søke og endre på alt.
 */
@Controller
public class AdminFagKontroller {
    @Autowired
    private EmneService EmneService;

    @RequestMapping("/searchFag.htm")
    public String finnEmne(@ModelAttribute("emnerBeans") EmnerBeans emnerBeans, @ModelAttribute("emne") Emne emne, Model modell, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String emner = request.getParameter("srch-term");
        if (emner == null) {
            if (session.getAttribute("soek") != null) {
                emnerBeans.setValgt(EmneService.finnEmne((String) session.getAttribute("sok")));
                session.setAttribute("soek", (String) session.getAttribute("sok"));
            }
        } else {
            emnerBeans.setValgt(EmneService.finnEmne(emner));
            session.setAttribute("soek", emner);
        }
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("emnerBeans", emnerBeans);
        return "adminFag";
    }

    @RequestMapping(value = "/slettEmne.htm", method = RequestMethod.POST)
    public String slettEmne(Model modell, @ModelAttribute("emnerBeans") EmnerBeans emnerBeans, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String emnekode = request.getParameter("emneIndex");
        EmneService.slettEmne(emnekode);
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("emnerBeans", emnerBeans);
        return "/searchFag.htm";
    }


    @RequestMapping(value = "/redigerEmne.htm", method = RequestMethod.POST)
    public String redigerEmne(@ModelAttribute("emne") Emne emne, Model modell, HttpServletRequest request, HttpSession session) {
        String emnekode = request.getParameter("emneIndex");
        Emne redigerEmne = EmneService.hentEmneNavn(emnekode);
        session.setAttribute("redigerEmne", redigerEmne);
        if (redigerEmne == null) {
            modell.addAttribute("melding", "Finner ikke emne i databasen");
            return "adminEmneEndre";
        } else {
            return "adminEmneEndre";
        }

    }

    @RequestMapping(value = "/redigerEmneLagre.htm")
    public String redigerEmneLagre(@Valid @ModelAttribute("emne") Emne emne, BindingResult result, Model modell, HttpServletRequest request, HttpSession session) {
        Emne redigerEmne =(Emne) session.getAttribute("redigerEmne");
        System.out.println(redigerEmne.getEmneKode() +", "+ emne);
        if (result.hasErrors()) {
            modell.addAttribute("melding", "FEIL: Fyll inn alle feltene");
            return "adminEmneEndre";
        }
        try {
            if (EmneService.oppdaterEmne(redigerEmne.getEmneKode(), emne)) {
                modell.addAttribute("Vellykket", "REGISTRERT Endringer for emne: " + emne.getEmneKode());
                session.removeAttribute("redigerEmneKode");
                return "adminEmneEndre";
            } else {
                modell.addAttribute("melding", "FEIL: Greide ikke å endre databasen");
                return "adminEmneEndre";
            }

        } catch (Exception e) {
            modell.addAttribute("melding", "En uventet feil oppsto<br><br>" + e);
            return "adminEmneEndre";
        }
    }

}
