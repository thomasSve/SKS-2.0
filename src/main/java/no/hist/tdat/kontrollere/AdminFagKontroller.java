package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.EmnerBeans;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Thomas on 23.01.14.
 */

/**
 * TODO Thomas: Endre slik at faglærer kun kan søke og endre på fag som den personen er foreleser i. Admin kan søke og endre på alt.
 */
@Controller
public class AdminFagKontroller {
    @Autowired
    private EmneService EmneService;

    @RequestMapping("/searchFag.htm")
    public String finnBruker(@ModelAttribute("emnerBeans") EmnerBeans emnerBeans, @ModelAttribute("emne") Emne emne, Model modell, HttpServletRequest request, HttpSession session) {
        String tab = request.getParameter("tab");
        String emner = request.getParameter("srch-term");
        if(emner == null){
            if(session.getAttribute("sok")!=null){
                emnerBeans.setValgt(EmneService.finnEmne((String)session.getAttribute("sok")));
                session.setAttribute("sok", (String)session.getAttribute("sok"));
            }
        }else{
            emnerBeans.setValgt(EmneService.finnEmne(emner));
            session.setAttribute("sok", emner);
        }
        modell.addAttribute("tabForm", tab);
        modell.addAttribute("emnerBeans", emnerBeans);
        return "adminFag";
    }

}
