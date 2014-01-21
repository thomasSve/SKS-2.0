package no.hist.tdat.kontrollere;

import no.hist.tdat.database.verktoy.KoeGruppeKoordinerer;
import no.hist.tdat.javabeans.KoeGrupper;
import no.hist.tdat.javabeans.Oving;
import no.hist.tdat.javabeans.beanservice.EmneService;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Olve André on 20.01.14.
 */
@Controller
public class GodkjennKontroller {
    @Autowired
    EmneService service;

    @RequestMapping(value = "/hentUtKoGruppe.htm")
    public String hentUtKoGruppe(@ModelAttribute("koeGrupper") KoeGrupper koeGrupper, HttpServletRequest request){
        String gruppeIndex = request.getParameter("brukerLederIndex");
        return "godkjennOving";
    }







/*
    @RequestMapping(value = "/godkjenn.htm")
    public String hentForstIKoe(@ModelAttribute("godkjennOving") KoeBruker koeBruker, Model model,@ModelAttribute("oving") Oving oving, HttpServletRequest request) {
        String konummer = request.getParameter("koplass");
        koeBruker.setKoe_plass(service.HentForstIKoe());
        //service.oppdaterBruker(oving, mail, ov_id);
        return "koeBruker";
    }                */

}
