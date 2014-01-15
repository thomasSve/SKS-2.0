package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Eirik on 13.01.14.
 */
@Controller
public class AdminBrukereKontroller {
    @Autowired
    BrukerService service;
    @RequestMapping(value="leggTilFil.html",method= RequestMethod.POST)
    public String test (HttpServletRequest req){
        String text = req.getParameter("newText");
        String[] linje = text.split("\\r?\\n");
        String epost, fnavn, enavn = "";
        for (int i = 0; i < linje.length; i++) {
            String[] po = linje[i].split(",");
            epost = po[0];
            fnavn = po[1];
            enavn = po[2];
            service.leggTilBruker(new Bruker(epost, Bruker.STUDENT_RETTIGHET, fnavn, enavn));
        }
        return "adminBrukere";
    }
}
