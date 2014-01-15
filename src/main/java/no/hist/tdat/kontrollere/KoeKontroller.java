package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Henriette on 09/01/14.
 */
@Controller
public class KoeKontroller {

  /*  @RequestMapping(value="regKoe")
    public String Koe(@Validated @ModelAttribute("koeBruker") KoeBruker koeBruker, BindingResult error, Model modell, HttpServletRequest request){


    }*/

   /* @RequestMapping(value="")
    public String hvilketRom(@ModelAttribute("koeBruker") KoeBruker koeBruker){


    }*/


    @Autowired
    EmneService service;
    @RequestMapping(value="/startKoe.htm")
    public String startKoen(@ModelAttribute DelEmne emne) {
        emne.setKoe_status(true);
        int koe_id = 0;
        service.endreKoeStatus(koe_id, 1);
        return "koOversikt";
    }
    @RequestMapping(value="/stoppKoe.htm")
    public String stoppKoen(@ModelAttribute DelEmne emne) {
        emne.setKoe_status(false);
        int koe_id = 0;
        service.endreKoeStatus(koe_id, 0);
        return "koOversikt";
    }
}
