package no.hist.tdat.kontrollere;

import no.hist.tdat.koe.Koe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Henriette on 09/01/14.
 */
@Controller
public class KoeKontroller {

    @RequestMapping(value="leggTilIKoe")
    public String Koe(Model model){
        Koe k = new Koe();
        model.addAttribute("koe", k);
        return "visKoe";

    }

   // @RequestMapping(value="regKoe")
    //public String registrerKoe(@ModelAttribute)

}
