package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.Emner;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Roger Foss
 */
@Controller
public class EndreStudentKontroller {
    @Autowired
    BrukerService service;
    @Autowired
    EmneService service2;

    @RequestMapping(value = "leggTilStudentListe")
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model modell, HttpServletRequest request) {
        String txt = request.getParameter("soketekst");
        personerBeans.setValgt(service.finnStudenter(txt));
        modell.addAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }


    @RequestMapping(value="endreValgtBruker", method = RequestMethod.POST)
    public String ananasbiter(Model modell,@ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request){
        String mail = request.getParameter("brukerIndex");
        Bruker bruker = service.hentBruker(mail);
        System.out.println(bruker.getFornavn());
        ArrayList<Emner> emner = service2.hentEmnerForStud(mail);
        System.out.println(emner.size());
        //service.slettBruker(mail.trim());
        modell.addAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }
}


/*
        System.out.println("nr: "+personerBeans.getValgt().size());

        for (int i = 0; i < 3; i++) {
            personerBeans.leggTil(valgtBruker);
        }

        ArrayList<Bruker> hei = new ArrayList<Bruker>();
        hei.add(new Bruker("sfasd@adsf.sk", 1, "Per", "Foss"));
        hei.add(new Bruker("sfasfdd@adsf.sk", 1, "Pål", "Hål"));
        hei.add(new Bruker("sfaaaa@adsf.sk", 1, "Gunnar", "Penis"));

        model.addAttribute("personerBeans", personerBeans);



    @RequestMapping(value="redigerStudent", method = RequestMethod.POST)
    public String finnBruker(Model modell,@ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request){
        //String mail = request.getParameter("brukerIndex");
        //service.slettBruker(mail.trim());
        //modell.addAttribute("personerBeans", personerBeans);
        return "endreValgtStudent";
    }


    @RequestMapping(value="fjernStudent")
    public String fjernStudent(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request, HttpServletResponse response) {
        int radNr = -1;
        for (Integer i = 0; i < personerBeans.getValgt().size(); i++) {
            String knappNrVar = request.getParameter(i.toString());
            if (knappNrVar != null && !knappNrVar.equals("")) {
                radNr = Integer.parseInt(knappNrVar);
                break;
            }
        }

        personerBeans.fjernStudent(radNr);
        model.addAttribute("personerBeans", personerBeans);


    @RequestMapping(value = "redigerStudent")
    public String bekreftelse(@ModelAttribute("personerBeans") PersonerBeans personerBeans, Model model, HttpServletRequest request) {
        String opersasjon = request.getParameter("opValg");
        ArrayList<Emner> emner = new ArrayList<Emner>();


        return "endreValgtStudent";
    }


    @RequestMapping(value = "fjernStudent")
    public String fjernStudent(@ModelAttribute("personerBeans") PersonerBeans personerBeans, @ModelAttribute("bruker") Bruker bruker, Model model, HttpServletRequest request) {

         int radNr = -1;
         for (Integer i = 0; i < personerBeans.getValgt().size(); i++) {

         String knappNrVar = request.getParameter(i.toString());
         if (knappNrVar != null && !knappNrVar.equals("")) {
         radNr = Integer.parseInt(knappNrVar);
         break;

        System.out.println(request.getParameter("index"));
        String index = request.getParameter("index");
        Integer indexen = Integer.parseInt(index);

        service.slettBruker(personerBeans.getValgt().get(indexen));
        model.addAttribute("personerBeans", personerBeans);

        return "endreStudent";
    }
    */

