package no.hist.tdat.kontrollere;

import no.hist.tdat.utils.lesInnFil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by Eirik on 13.01.14.
 */
@Controller
public class AdminBrukereKontroller {
    @RequestMapping(value="leggTilFil.html")
    public String test (@ModelAttribute(value="lesInnFil")lesInnFil text) throws IOException {
        System.out.println(text.getText());
        return "adminBrukere.htm";
    }
}
