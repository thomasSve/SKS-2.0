package no.hist.tdat.kontrollere;

import no.hist.tdat.koe.KoeBruker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }
    @RequestMapping("/endrePassord.htm")
    public String omdirigerEndrePassord() {
        return "endrePassord";
    }
    @RequestMapping("/adminBrukere.htm")
    public String omdirigerAdminBrukere() {
        return "adminBrukere";
    }
    @RequestMapping("/adminFag.htm")
    public String omdirigerAdminFag() {
        return "adminFag";
    }
    @RequestMapping("/error.htm")
    public String omdirigerError(){
        return "error";
    }
    @RequestMapping("/settIKo.htm")
    public String omdirigerTilKo(Model model){
        KoeBruker koeBruker = new KoeBruker();
        model.addAttribute("bruker", koeBruker);
        return "settIKo";
    }
    @RequestMapping("/endreStudent.htm")
    public String omdirEndreStudent(){
        return "endreStudent";
    }
    @RequestMapping("/minside.htm")
    public String omdirigerMinside() {
        return "minside";
    }
    @RequestMapping("/login.htm")
    public String loggInn(){
        return "loggInn";
    }
    @RequestMapping("/ovingsOpplegg.htm")
    public String ovingsOpplegg(){
        return "ovingsOpplegg";
    }
    @RequestMapping("/*")
    public String direct404() {
        return "error";
    }

}