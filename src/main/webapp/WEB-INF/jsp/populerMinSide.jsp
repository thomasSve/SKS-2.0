<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%
    Bruker denne = (Bruker) request.getSession().getAttribute("innloggetBruker");
    for (int i = 0; i < denne.getEmne().size(); i++) {
        out.println("<tr ><td>" + i + "</td><td>" + denne.getEmne().get(i).getEmneKode() + "</td><td><button class='btn btn-lg btn-primary btn-block'>" + denne.getEmne().get(i).getEmneNavn() + "</button></td></tr>");

        for (int j = 0; j < denne.getEmne().get(i).getDelemner().size(); j++) {

            out.print("<tr><td></td><td></td><td><p onclick='hentKoeStudent("+denne.getEmne().get(i).getDelemner().get(j).getKoe_id()+")' class='pull-right btn btn-md btn-info' style='width:50%;'>" + denne.getEmne().get(i).getDelemner().get(j).getDelEmneNavn() + "</p></td><td>");
            for (int a = 0; a < denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().size(); a++) {
                if (denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).isGodkjent()) {
                    String godkjentInfo = "Godkjent av:\t " + denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getGodkjentAv() + "\ndato:\t\t" + denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getGodkjentTid();
                    out.println("<button class ='btn btn-success btn-sm active' title='" + godkjentInfo + "'>" + denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getOvingnr() + "</button>");
                } else {
                    out.println("<button class ='btn btn-default btn-sm disabled' >" + denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getOvingnr() + "</button>");
                }
            }
            out.println("</td></tr>");
        }
    }
%>