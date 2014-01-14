package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }

    @RequestMapping("/endrePassord.htm")
    public String omdirigerEndrePassord(@ModelAttribute(value = "passord") PassordBeans passord) {
        return "endrePassord";
    }

    @RequestMapping("/adminBrukere.htm")
    public String omdirigerAdminBrukere() {
        return "adminBrukere";
    }
    @RequestMapping("/godkjennOving.htm")
    public String omdirigerGodkjenn() {
        return "godkjennOving";
    }
    @RequestMapping("/adminFag.htm")
    public String omdirigerAdminFag() {
        return "adminFag";
    }

    @RequestMapping("/glemtPassord.htm")
    public String glemtPassord(@ModelAttribute("bruker") Bruker bruker) {
        return "glemtPassord";
    }

    @RequestMapping("/koOversikt.htm")
    public String koOversikt() {
        return "koOversikt";
    }

    @RequestMapping("/error.htm")
    public String omdirigerError() {
        return "error";
    }

    @RequestMapping("/settIKo.htm")
    public String omdirigerTilKo() {
        return "settIKo";
    }

    @RequestMapping("/endreStudent.htm")
    public String omdirEndreStudent() {
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
    public String ovingsOpplegg() {
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