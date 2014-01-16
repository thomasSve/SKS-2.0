package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
    public String leggTilFil(@ModelAttribute Bruker bruker, HttpServletRequest req) {
        String text = req.getParameter("newText");
        String[] linje = text.split("\\r?\\n");
        String epost, fnavn, enavn = "";
        try {
            int teller = 0;
            while(teller<linje.length){
                try{
                String[] po = linje[teller].split(", ");
                epost = po[0].toLowerCase().trim();
                fnavn = po[1];
                enavn = po[2];
                service.leggTilBruker(new Bruker(epost, Bruker.STUDENT_RETTIGHET, fnavn, enavn));
                }catch (org.springframework.dao.DuplicateKeyException t){teller++;
                }
                teller++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Finner ingen tekst i filen. " + e);
            return "adminBrukere";
        }

        return "adminBrukere";
    }
}
