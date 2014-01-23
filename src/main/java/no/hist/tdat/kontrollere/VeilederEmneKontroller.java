package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Emne;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Kontrollerer
 *
 * @author Jørgen
 *
 *
 */
@Controller
@RequestMapping("/administrerEmne")
public class VeilederEmneKontroller {

    @RequestMapping("*/")
    public String landeside(@ModelAttribute("emner") Emne emne){
        return "mineFag";
    }

    @RequestMapping("/emne/*")
    public String detteEmnet(){
        return "test";
    }


}
