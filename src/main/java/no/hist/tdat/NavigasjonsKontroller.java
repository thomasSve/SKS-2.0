package no.hist.tdat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {

    @RequestMapping("/")
    public String omdirigerHjem() {
        System.out.println("Hello");
        return "index";
    }
}