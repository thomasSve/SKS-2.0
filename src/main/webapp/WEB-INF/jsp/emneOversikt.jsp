<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ page import="no.hist.tdat.javabeans.Emne" %>

<%
    Bruker innlogget = (Bruker)session.getAttribute("innloggetBruker");
    Emne emne = null;
    String emnekode = (String)session.getAttribute("emnekodeFraMeny");
    int i = 0;
    for (int t = 0; t <innlogget.getEmne().size() ; t++) {
        if(innlogget.getEmne().get(t).getEmneKode().equals(emnekode)){
            emne = innlogget.getEmne().get(t);
            i = t;
        }
    }
%>

<div class="col-md-8">
    <ul class="nav nav-tabs nav-justified">
        <li id="lenkeEmne" class="active"><a href="#emne" data-toggle="tab"><% out.print(emne.getEmneNavn());%></a></li>
        <li id="lenkeOvinger"><a href="#ovinger" data-toggle="tab">Øvingsoversikt</a></li>
    </ul>

    <div class="tab-content">

        <div class="tab-pane fade in active" id="emne">



            <div class="col-md-8">
<%--                <h3>${emnekodeFraMeny.getEmnenavn()}</h3>--%>
                <div class="row">
                    <div class=" table-responsive">

                        <table class="col-lg-10 table table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Delemne</th>
                                <th>&Oslash;vingsoversikt</th>
                            </tr>
                            </thead>
                            <tbody>

                            <form onsubmit="mysubmit()" method="POST" action="koOversikt.htm">
                                <input type="hidden" name="delemneNr" id="delemneNr" />
                                <input type="hidden" name="emneNr" id="emneNr" />


                                    <%


                                    for (int j = 0; j < emne.getDelemner().size(); j++) {

                                        out.print("<tr><td></td><td><input type='submit' onclick='delemnenr="+j+";emnenr="+i+"' value ='"+emne.getDelemner().get(j).getDelEmneNavn()+"' class='pull-right btn btn-md btn-info btn-block'  /></td><td>");
                                        for (int a = 0; a < emne.getDelemner().get(j).getStudentovinger().size(); a++) {
                                            if (emne.getDelemner().get(j).getStudentovinger().get(a).isGodkjent()) {
                                                String godkjentInfo = "Godkjent av:\t " + emne.getDelemner().get(j).getStudentovinger().get(a).getGodkjentAv() + "\ndato:\t\t" + emne.getDelemner().get(j).getStudentovinger().get(a).getGodkjentTid();
                                                out.println("<button class ='btn btn-success btn-sm active' title='" + godkjentInfo + "'>" + emne.getDelemner().get(j).getStudentovinger().get(a).getOvingnr() + "</button>");
                                            } else {
                                                out.println("<button class ='btn btn-default btn-sm disabled' >" + emne.getDelemner().get(j).getStudentovinger().get(a).getOvingnr() + "</button>");
                                            }
                                        }
                                        out.println("</td></tr>");

                                    }
                                    %>
                            </form>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>

        <div class="tab-pane fade" id="ovinger">
            CONTENT PÅ ANDRE SIDE
        </div>


    </div>
</div>

<script src="<c:url value="/resources/js/koen.js"/>"></script>