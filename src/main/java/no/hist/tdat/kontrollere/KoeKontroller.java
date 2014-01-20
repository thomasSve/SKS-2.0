package no.hist.tdat.kontrollere;



import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Plassering;
import no.hist.tdat.javabeans.beanservice.EmneService;
import no.hist.tdat.javabeans.beanservice.KoeService;
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
    @Autowired
    KoeService koe_service;

    @Autowired
    EmneService emne_service;

    @RequestMapping(value="/startKoe.htm")
    public String startKoen(@ModelAttribute DelEmne emne) {
        emne.setKoe_status(true);
        emne_service.endreKoeStatus(emne.getKoe_id(), 1);
        return "koOversikt";
    }

    @RequestMapping(value="velgPlass.htm")
    public String Koe(@Validated @ModelAttribute("plassering") Plassering plassering, BindingResult error, Model modell, HttpServletRequest request){
        if(error.hasErrors()){
            return "sittIKo.htm";
        }

        try{
            String plass = request.getParameter("sitteplass");
            koe_service.getAntBord(plass);
            int bordnret = request.getIntHeader("bordnr");

        }catch(NullPointerException e){
            System.out.println("Du må være logget inn før du kan sette  deg i kø!");
            return "koOversikt";
        }
        return "sittIKo.htm";

    }
    @RequestMapping(value="/stoppKoe.htm")
    public String stoppKoen(@ModelAttribute DelEmne emne) {
        emne.setKoe_status(false);
        emne_service.endreKoeStatus(emne.getKoe_id(), 0);
        return "koOversikt";
    }
}
