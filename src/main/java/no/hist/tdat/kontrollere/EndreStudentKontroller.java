package no.hist.tdat.kontrollere;


import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.PersonerBeans;
import no.hist.tdat.javabeans.beanservice.BrukerService;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String leggTilListe(@ModelAttribute("personerBeans") PersonerBeans personerBeans, HttpServletRequest request, HttpSession session) {
        String txt = request.getParameter("soketekst");
        if (txt == null || txt.equals("")) {
            return "endreStudent";
        }
        personerBeans.setValgt(service.finnStudenter(txt));
        session.setAttribute("personerBeans", personerBeans);
        return "endreStudent";
    }


    @RequestMapping(value = "endreValgtBruker", method = RequestMethod.POST)
    public String endreValgtBruker(HttpServletRequest request, HttpSession session) {
        String mail = request.getParameter("brukerIndex");
        Bruker b = service.hentBruker(mail);
        b.setEmne(service2.hentEmnerForStud(b.getMail()));

        session.setAttribute("emnerUtenTilgang", service2.hentEmnerUtenTilgang(b.getMail()));
        session.setAttribute("valgtPerson", b);
        return "endreValgtStudent";
    }

    @RequestMapping(value = "videresend")
    public String videresend(HttpServletRequest request, HttpSession session) {
        String tilb = request.getParameter("tilbake");
        if (tilb != null) {
            session.removeAttribute("valgtPerson");
            return "endreStudent";
        }
        return "endreValgtStudent";
    }

    @RequestMapping(value = "utførOperasjon")
    public String utførOperasjon(Model modell, HttpServletRequest request, HttpSession session) {
        String lagre = request.getParameter("lagre");
        String fjern = request.getParameter("fjern");

        Bruker b = (Bruker) session.getAttribute("valgtPerson");

        if (lagre != null) {    //sett som studass
            String emnekode = request.getParameter("emner");
   //         if (service.settStudass(emnekode,"DELEMNE",b.getMail())) {
                modell.addAttribute("forrigeOp", b.getFornavn() + " " + b.getEtternavn() + " satt som studass i " + emnekode);
                System.out.println("Setter "+b.getEtternavn()+" som studass i "+emnekode);
   //         }
        }
        else if (fjern != null) {   //fjern fag
            String emnekode2 = request.getParameter("emner2");
            if (service.fjernEmne(emnekode2,b.getMail())) {
                modell.addAttribute("forrigeOp", "Fjernet rettighet til emnet " + emnekode2 + " for "+b.getFornavn()+" "+ b.getEtternavn());
                b.setEmne(service2.hentEmnerForStud(b.getMail()));
                session.setAttribute("valgtPerson",b);
                System.out.println("fjerner "+emnekode2 + " for "+b.getEtternavn());
            }
        }
        else {  //legg til fag
            String emnekode3 = request.getParameter("emner3");
            if (service.leggTilEmne(emnekode3,b.getMail(), 0)) {
                modell.addAttribute("forrigeOp", "Tilgang til emnet " + emnekode3 + " lagt til for "+b.getFornavn()+" "+b.getEtternavn());
                System.out.println("legger til "+emnekode3 + " for "+b.getEtternavn());
            }
        }



        return "endreValgtStudent";

    }

    //ferdig, men ikke testet
    @RequestMapping(value = "gjorTilStudass")
    public String settStudass(Model modell, HttpServletRequest request, HttpSession session) {
        String emnekode = request.getParameter("emner");
        System.out.println(emnekode);
        String emnekode2 = request.getParameter("emner2");
        System.out.println("nr2: " + emnekode2);
        String emnekode3 = request.getParameter("emner3");
        System.out.println("nr3: " + emnekode3);

        Bruker b = (Bruker) session.getAttribute("valgtPerson");
        //service.settStudass(emnekode,"DELEMNE",b.getMail()); //kan legges i if, for å få tilbakemeld, bruker boolean
        return "endreValgtStudent";

    }

    //ferdig, men ikke testet
    @RequestMapping(value = "leggTilFag")
    public String leggTilFag(HttpServletRequest request, HttpSession session) {
        String emnekode = request.getParameter("emner");
        System.out.println("nr1: " + emnekode);
        String emnekode2 = request.getParameter("emner2");
        System.out.println("nr2: " + emnekode2);
        String emnekode3 = (String) request.getAttribute("emner3");
        System.out.println("nr3: " + emnekode3);

        Bruker b = (Bruker) session.getAttribute("valgtPerson");
        //service.leggTilEmne(emnekode,b.getMail(), 0); //kan legges i if, for å få tilbakemeld, bruker boolean
        return "endreValgtStudent";

    }

    //ferdig, men ikke testet
    @RequestMapping(value = "fjernFag")
    public String fjernFag(Model modell, HttpServletRequest request, HttpSession session) {

        Bruker b = (Bruker) session.getAttribute("valgtPerson");
        //      if (service.fjernEmne(emnekode,b.getMail())) {
        //modell.addAttribute("forrigeOp", "Fjernet rettighet til emnet " + emnekode);
        //       }
        //session.setAttribute("valgtPerson", service.g);
        return "endreValgtStudent";

    }
}


