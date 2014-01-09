package no.hist.tdat.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test.htm")
    public String redirect()
    {
        return "test";
    }
}
