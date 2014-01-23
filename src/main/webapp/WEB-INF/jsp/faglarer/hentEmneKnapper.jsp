<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%
    Bruker denne = (Bruker) request.getSession().getAttribute("innloggetBruker");
    for (int i = 0; i < denne.getEmne().size(); i++) {
        out.println("<tr ><td>" + i + "</td><td>" + denne.getEmne().get(i).getEmneKode() + "</td><td><button onClick=\"openEmne(\'"+denne.getEmne().get(i).getEmneKode()+"\')\" class='btn btn-lg btn-primary btn-block'>" + denne.getEmne().get(i).getEmneNavn() + "</button></td></tr>");
    }
%>