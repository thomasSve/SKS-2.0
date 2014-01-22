package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.*;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.EmneService;
import no.hist.tdat.javabeans.beanservice.KoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    EmneService emneService;

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
    public String omdirigerAdminBrukere(@ModelAttribute Bruker bruker, @ModelAttribute PersonerBeans personerBeans) {
        return "adminBrukere";
    }

    @RequestMapping("/adminBrukereEndre.htm")
    public String omdirigerAdminBrukereEndre(@ModelAttribute Bruker bruker, @ModelAttribute PersonerBeans personerBeans) {
        return "adminBrukereEndre";
    }

    @RequestMapping("/godkjennOving.htm")
    public String omdirigerGodkjenn() {
        return "godkjennOving";
    }

    @RequestMapping("/adminFag.htm")
    public String omdirigerAdminFag() {
        return "adminFag";
    }

    @RequestMapping(value = "/koOversikt.htm", method = RequestMethod.POST)
    public String koOversikt(@ModelAttribute("delEmne") DelEmne delEmne, HttpServletRequest request, HttpSession session, Model model) {
        int delemneNr = Integer.parseInt(request.getParameter("hiddenKoe"));
        int emnenr = Integer.parseInt(request.getParameter("hiddenEmneNavn"));
        innloggetBruker = (Bruker) session.getAttribute("innloggetBruker");
        delEmne = innloggetBruker.getEmne().get(emnenr).getDelemner().get(delemneNr);
        int koeId = delEmne.getKoe_id();
        Koe koe = new Koe();
        koe.setGrupper(koeservice.getKoe(koeId));
        koe.setKoeId(koeId);
        DelEmne denne = koeservice.hentDelEmneStatus(koeId);
        delEmne.setKoe_status(denne.isKoe_status());
        ArrayList<KoeGrupper> grupper = koe.getGrupper();
        model.addAttribute("koe", koe);
        model.addAttribute("grupper", grupper);
        model.addAttribute("delEmne", delEmne);
        return "koOversikt";
    }

    @RequestMapping("/error.htm")
    public String omdirigerError() {
        return "error";
    }


    @RequestMapping(value = "/settIKo.htm", method = RequestMethod.POST)
    public String omdirigerTilKo(@ModelAttribute("personerBeans") PersonerBeans personerBeans, @ModelAttribute("bruker") Bruker bruker,
                                 @ModelAttribute("koegrupper") KoeGrupper koegrupper, @ModelAttribute("delEmne") DelEmne delEmne,
                                 Model model, HttpSession session, HttpServletRequest request) {

        innloggetBruker = (Bruker) session.getAttribute("innloggetBruker");
        int koe_id = Integer.parseInt(request.getParameter("KoeIndex"));
        personerBeans.setValgt(service.getMedstudenter(delEmne.getDelEmneNavn(), innloggetBruker.getMail()));
        model.addAttribute("personerBeans", personerBeans);
        //koeservice.getPlasseringer();
        DelEmne denne = koeservice.hentDelEmneStatus(koe_id);
        delEmne.setKoe_status(denne.isKoe_status());
        //model.addAttribute("plassering", koeservice);
        model.addAttribute("delEmne", delEmne);

        return "settIKo";
    }

    @RequestMapping("/endreStudent.htm")
    public String omdirEndreStudent(@ModelAttribute("bruker") Bruker bruker) {
        return "endreStudent";
    }

    @RequestMapping("/minside.htm")
    public String omdirigerMinside(@ModelAttribute("bruker") Bruker bruker, HttpSession session) {
        bruker = (Bruker) session.getAttribute("innloggetBruker");
        return "minside";
    }

    @RequestMapping("/emne.htm")
    public String hentMittEmne(@ModelAttribute("bruker") Bruker bruker, HttpSession session) {
        bruker = (Bruker) session.getAttribute("innloggetBruker");
        bruker.getEmne().get(0);
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

        //HENTER FOR ETIKK
    @RequestMapping("/godkjenningsoversikt.htm")
    public String godkjOversikt(HttpServletRequest request, Model modell, HttpSession session) {

        String emne = "ingen";//request.getParameter("emne");
        if (emne.equals("ingen")) {
            return "godkjenningsoversikt";
        }

        DelEmne valgtEmne = emneService.hentDelemne(emne);
        session.setAttribute("valgteEmne", valgtEmne);

        return "godkjenningsoversikt";

    }

    @RequestMapping("/opprettEmne.htm")
    public String opprettEmne(@ModelAttribute("emne") Emne emne) {
        return "opprettEmne";
    }
}