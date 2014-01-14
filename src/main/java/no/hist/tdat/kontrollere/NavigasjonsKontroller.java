package no.hist.tdat.kontrollere;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import no.hist.tdat.javabeans.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {
    @Autowired
    private Bruker innloggetBruker;

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }

    @RequestMapping("/endrePassord.htm")
    public String omdirigerEndrePassord(@ModelAttribute(value = "passord") PassordBeans passord) {
        return "endrePassord";
    }

    @RequestMapping("/sendNyttPassord.htm")
    public String glemtPassord(@ModelAttribute("bruker") Bruker bruker) {
        /*if(bruker.getGammeltPassord().equals(bruker.getPassord())){

        }*/
        return "glemtPassord";
    }

    @RequestMapping("/leggTilBruker.htm")
    public String leggTilBruker(@ModelAttribute Bruker bruker, Model modell) {
        modell.addAttribute("bruker", bruker);
        if(bruker.getMail()!=null){
            //bruker.leggTilBruker();
        }
        return "adminBrukere";
    }

    @RequestMapping("/adminBrukere.htm")
    public String omdirigerAdminBrukere(@ModelAttribute Bruker bruker) {
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
    public String omdirigerMinside(HttpSession session) {
        return "minside";
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

    @RequestMapping("/loggUt.htm")
    public String loggUt(HttpSession session) {
        session.invalidate();
        System.out.println("utlogget");
        return "/";
    }

}