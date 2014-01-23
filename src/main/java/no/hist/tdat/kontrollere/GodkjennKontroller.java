package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.Koe;
import no.hist.tdat.javabeans.KoeGrupper;
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
 * Created by Olve André on 20.01.14.
 */
@Controller
public class GodkjennKontroller {
    @Autowired

    EmneService service;


    @RequestMapping(value = "/hentUtKoGruppe.htm", method = RequestMethod.POST)
    public String hentUtKoGruppe(@ModelAttribute("koeGrupper") KoeGrupper koeGrupper, Model modell, HttpServletRequest request, HttpSession session){

        Koe koe = (Koe)session.getAttribute("koe");

        String gruppeNokkler = request.getParameter("gruppeIndexFraKoe");
        String[] nokler = gruppeNokkler.split(":");
        KoeGrupper gruppe = null;
        for (int i = 0; i <koe.getGrupper().size() ; i++) {
            if(koe.getGrupper().get(i).getGruppeID()==Integer.parseInt(nokler[1]) && koe.getGrupper().get(i).getKoe_id()==Integer.parseInt(nokler[0])){
                gruppe = koe.getGrupper().get(i);
                System.out.println(gruppe.getKoe_id()+ "I kontroller");
            }
        }
        session.setAttribute("gruppeFraKoe", gruppe);

        return "godkjennOving";

    }

    @RequestMapping(value = "/godkjennGruppeOving.htm", method = RequestMethod.POST)
    public String godkjennGruppeOving(Model modell, HttpServletRequest request, HttpSession session) {
        String godkjenn = request.getParameter("godkjennKnapp");
        String leggTilStudenter = request.getParameter("leggTilStundeterKnapp");
        String leggTilOving = request.getParameter("endreOvingerKnapp");

        KoeGrupper koeGrupper = (KoeGrupper)session.getAttribute("gruppeFraKoe");
        Bruker personenSomGodkjenner = (Bruker)session.getAttribute("innloggetBruker");
        System.out.println("dette er rart");

        if (godkjenn != null) {
            System.out.println("dette er feil lol ");

            for(int i = 0; i < koeGrupper.getMedlemmer().size(); i++) {
                System.out.println("Skjer'a?");
                if (koeGrupper.getOvinger().size() == 0) {
                    System.out.println("hei");

                }
                if (koeGrupper.getOvinger().size() == 1) {
                    System.out.println("hei");
                }
                if (koeGrupper.getOvinger().size() <= 2) {
                    String[] ovingerSomSkalGodkjennes = koeGrupper.getOvingerIString().split(",");
                    System.out.println("slask");
                    for(int j = 0; ovingerSomSkalGodkjennes.length > j ; j++) {
                        String randomNummerLol = ovingerSomSkalGodkjennes[j];
                        randomNummerLol.trim();
                        int ovingsnummer = Integer.parseInt(randomNummerLol);

                    }
                }
            }
        }

        return "godkjennOving";
    }
}

