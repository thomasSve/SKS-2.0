package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PassordBeans;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.Plassering;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.KoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class NavigasjonsKontroller {

    @Autowired
    Bruker innloggetBruker;

    @Autowired
    BrukerService service;

    @Autowired
    KoeService koeservice;

    @RequestMapping("/")
    public String omdirigerHjem() {
        return "index";
    }

    @RequestMapping("/endrePassord.htm")
    public String omdirigerEndrePassord(@ModelAttribute(value = "passord") PassordBeans passord) {
        return "endrePassord";
    }

    @RequestMapping("/glemtPassord.htm")
    public String glemtPassord(@ModelAttribute Bruker bruker) {
        return "glemtPassord";
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
        return   "error";
    }

    @RequestMapping("/settIKo.htm")
    public String omdirigerTilKo(@ModelAttribute("personerBeans") PersonerBeans personerBeans,@ModelAttribute("bruker")Bruker bruker, @ModelAttribute("plassering") Plassering plassering, Model model, HttpSession session){
        innloggetBruker= (Bruker)session.getAttribute("innloggetBruker");
        System.out.println(innloggetBruker.getFornavn());
        personerBeans.setValgt(service.getMedstudenter("ALM802F-B", innloggetBruker.getMail()));
        model.addAttribute("personerBeans", personerBeans);
        koeservice.getPlasseringer();
        model.addAttribute("plassering", koeservice);

        return "settIKo";
    }

    @RequestMapping("/endreStudent.htm")
    public String omdirEndreStudent(@ModelAttribute("bruker") Bruker bruker) {
        return "endreStudent";
    }

    @RequestMapping("/minside.htm")
    public String omdirigerMinside(@ModelAttribute("bruker")Bruker bruker,HttpSession session) {
        bruker = (Bruker)session.getAttribute("innloggetBruker");
        return "minside";
    }

    @RequestMapping("/emne.htm")
    public String hentMittEmne(@ModelAttribute("bruker")Bruker bruker,HttpSession session) {
        bruker = (Bruker)session.getAttribute("innloggetBruker");
        bruker.getEmner().get(0);
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
    public String loggUt(@ModelAttribute("bruker") Bruker bruker, HttpSession session) {
        session.invalidate();
        System.out.println("utlogget");
        return "loggInn";
    }
}