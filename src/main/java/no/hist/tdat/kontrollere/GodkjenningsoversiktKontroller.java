package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.*;
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
import java.util.ArrayList;

/**
 * Created by vimCnett
 */

@Controller
public class GodkjenningsoversiktKontroller {
    @Autowired
    BrukerService service;
    @Autowired
    EmneService service2;

    @RequestMapping(value = "hentRiktigEmne", method = RequestMethod.POST)  //kalles av ajax
    public void hentRiktigEmne(HttpServletRequest request, HttpSession session) {
        String emne = request.getParameter("emne");

        ArrayList<Emne> em = new ArrayList<Emne>();
        Emne emnet = service2.hentEmne(emne);    //lager emnet
        em.add(emnet);

        DelEmne delEmne = service2.hentDelemne(emne);   // henter delemnet
        ArrayList<DelEmne> a = new ArrayList<DelEmne>();
        a.add(delEmne);

        ArrayList<Bruker> alle = service.finnStudenterIDelemne(delEmne.getDelEmneNavn());   //alle med faget

        for (int i = 0; i < alle.size(); i++) {
            Bruker br = alle.get(i);
            ArrayList<Oving> ovinger = service.hentOvinger(emne,br.getMail());    //henter øvinger til delemnet
                System.out.println(ovinger.size());
            delEmne.setStudentovinger(ovinger);
            emnet.setDelemner(a);
            br.setEmne(em);
        }

        System.out.println("Emne: "+emne);

        for (int i = 0; i < alle.size(); i++) {
            System.out.println(alle.get(i).getEtternavn()+ " ant øvinger:  "+alle.get(i).getEmne().get(0).getDelemner().get(0).getStudentovinger().size());
        }

        session.setAttribute("ovingsoversikt", alle);
    }
}
