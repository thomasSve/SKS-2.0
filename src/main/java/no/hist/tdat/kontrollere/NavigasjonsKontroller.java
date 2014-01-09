package no.hist.tdat.kontrollere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }
    @RequestMapping("/minside.htm")
    public String omdirigerMinside() {
        return "minside";
    }
}