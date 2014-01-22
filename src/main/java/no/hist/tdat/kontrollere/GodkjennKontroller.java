package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.KoeGrupper;
import no.hist.tdat.javabeans.Oving;
import no.hist.tdat.javabeans.beanservice.EmneService;
import no.hist.tdat.javabeans.beanservice.KoeService;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Olve André on 20.01.14.
 */
@Controller
public class GodkjennKontroller {
    @Autowired
    KoeService service;

    @RequestMapping(value = "/godkjennGruppeOving.htm")
    public String godkjennGruppeOving(@ModelAttribute("godkjennOving") KoeGrupper koeGrupper, Model modell, HttpServletRequest request, HttpSession session) {
        String godkjenn = request.getParameter("godkjennKnapp");
        String leggTilStudenter = request.getParameter("leggTilStundeterKnapp");
        String leggTilOving = request.getParameter("endreOvingerKnapp");
        String

        return "koeBruker";
    }

}
