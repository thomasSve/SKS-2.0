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

        ArrayList<Bruker> alle = service.finnStudenterIDelemne(emne);   //alle med faget

        for (int i = 0; i < alle.size(); i++) {
            ArrayList<Emne> em = new ArrayList<Emne>();
            ArrayList<DelEmne> a = new ArrayList<DelEmne>();

            Bruker br = alle.get(i);

            ArrayList<Oving> ovinger = service.hentOvinger(emne, br.getMail()); //henter øvinger til delemnet
/*
            for (int j = 0; j < ovinger.size(); j++) {
                Oving denne = ovinger.get(j);
                if (service.hentOvingGodkjent(br.getMail(), denne.getOvingid())) {

                }
            }
*/
            DelEmne delEmne = service2.hentDelemne(emne);   // henter delemnet
            delEmne.setStudentovinger(ovinger);
            a.add(delEmne);

            Emne emnet = service2.hentEmne(emne);    //lager emnet
            emnet.setDelemner(a);
            em.add(emnet);

            br.setEmne(em);
        }
        System.out.println(alle.get(0).getEmne().get(0).getDelemner().get(0).getStudentovinger().size());
        System.out.println(alle.get(3).getEmne().get(0).getDelemner().get(0).getStudentovinger().size());
        session.setAttribute("ovingsoversikt", alle);
    }
}