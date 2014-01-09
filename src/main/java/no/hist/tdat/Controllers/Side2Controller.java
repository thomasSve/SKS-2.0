package no.hist.tdat.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Side2Controller {

    @RequestMapping("/side2.htm")
    public String redirect()
    {
        return "side2";
    }
}
