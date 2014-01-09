package no.hist.tdat.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Side3Controller {

    @RequestMapping("/side3.htm")
    public String redirect()
    {
        return "side3";
    }
}
