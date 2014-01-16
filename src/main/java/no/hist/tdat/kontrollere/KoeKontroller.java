package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.Emner;
import no.hist.tdat.javabeans.Plassering;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.KoeService;
import no.hist.tdat.javabeans.koeGrupper;
import no.hist.tdat.koe.Koe;
import no.hist.tdat.koe.KoeBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Henriette on 09/01/14.
 */
@Controller
public class KoeKontroller {
    KoeService service;

    @RequestMapping(value="velgPlass.htm")
    public String Koe(@Validated @ModelAttribute("plassering") Plassering plassering, BindingResult error, Model modell, HttpServletRequest request){
        String plass = request.getParameter("sitteplass");
        service.getAntBord(plass);
        return "sittIKo.htm";

    }






}
