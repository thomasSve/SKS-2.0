package no.hist.tdat.kontrollere;

<<<<<<< HEAD
import no.hist.tdat.javabeans.Bruker;
import org.springframework.stereotype.Controller;
=======

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> ece1ed746dbaf98e8160deb1b3aa5831a8147448
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigasjonsKontroller {

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }

    @RequestMapping("/endrePassord.htm")
    public String omdirigerEndrePassord(@ModelAttribute("bruker") Bruker bruker) {
        return "endrePassord";
    }
    @RequestMapping("/sendNyttPassord.htm")
    public String glemtPassord(@ModelAttribute("bruker") Bruker bruker) {
        /*if(bruker.getGammeltPassord().equals(bruker.getPassord())){

        }*/
        return "glemtPassord";
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
    public String glemtPassord() {
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
<<<<<<< HEAD
    public String loggInn(@ModelAttribute("bruker") Bruker bruker){
=======
    public String loggInn() {
>>>>>>> ece1ed746dbaf98e8160deb1b3aa5831a8147448
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