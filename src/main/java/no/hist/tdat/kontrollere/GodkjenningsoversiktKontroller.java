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
        Emne emnet = new Emne();    //lager emnet
        emnet.setEmneKode(emne);

        DelEmne delEmne = service2.hentDelemne(emne);   // henter delemnet
        ArrayList<DelEmne> a = new ArrayList<DelEmne>();
        a.add(delEmne);
        emnet.setDelemner(a);

        ArrayList<Bruker> alle = service.finnStudenterIDelemne(delEmne.getDelEmneNavn());   //alle med faget



        for (int i = 0; i < alle.size(); i++) {
            Bruker br = alle.get(i);


            em.add(emnet);
            br.setEmne(em);

            ArrayList<Oving> ovinger = service.hentOvinger(emne);    //henter øvinger til delemnet
        }


        emnet.setDelemner(a);   //setter delemnet inn i emnet
    }
}
