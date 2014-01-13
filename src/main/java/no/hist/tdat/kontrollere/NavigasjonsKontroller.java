package no.hist.tdat.kontrollere;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import no.hist.tdat.javabeans.*;

import javax.servlet.http.HttpServletRequest;

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
    public String omdirigerAdminBrukere(@ModelAttribute Bruker bruker, Model modell) {
        modell.addAttribute("leggTilBruker", bruker);
        System.out.println("Object: "+ bruker);
        System.out.println("mail til bruker: " + bruker.getMail());
        if(bruker.getMail()!=null){
            bruker.leggTilBruker();
        }
        return "adminBrukere";
    }
/*   @RequestMapping("/adminBrukere.htm")
    public String omdirigerAdminBrukere() {
        return "adminBrukere";
    }*/
    @RequestMapping("/adminFag.htm")
    public String omdirigerAdminFag() {
        return "adminFag";
    }
    @RequestMapping("/glemtPassord.htm")
    public String glemtPassord() {
        return "glemtPassord";
    }
    @RequestMapping("/koOversikt.htm")
    public String koOversikt() {
        return "koOversikt";
    }
    @RequestMapping("/error.htm")
    public String omdirigerError(){
        return "error";
    }
    @RequestMapping("/settIKo.htm")
    public String omdirigerTilKo(){
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
    public String loggInn(@ModelAttribute("bruker") Bruker bruker){
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
    @RequestMapping("/nyStudent.htm")
    public String nyStudent(@ModelAttribute("nyStudent") Bruker stud) {
        return "nyStudent";
    }

}