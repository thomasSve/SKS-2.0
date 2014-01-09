package no.hist.tdat.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Side4Controller {

    @RequestMapping("/side4.htm")
    public String redirect()
    {
        return "side4";
    }
}
