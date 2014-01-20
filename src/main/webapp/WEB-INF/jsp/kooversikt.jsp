<%@ page import="no.hist.tdat.javabeans.DelEmne" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 10.01.14
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="pull-left col-md-8">
    <h3>
        <div id="operasjonstekst">
            K&oslash; for (<c:out value="${delEmne.delEmneNavn}"/>)
        </div>
    </h3>
    <a href="settIKo.htm">
        <button class="btn btn-sm btn-primary">Still i k&oslash;</button>
    </a>
    <%
        DelEmne delEmne = (DelEmne)request.getAttribute("delEmne");
        if(delEmne.isKoe_status()){
            out.println("<input type=\"button\" onclick=\"startStoppKoe(" + delEmne.getNr() +")\" class=\"btn btn-sm btn-danger\" id=\"stoppKoe\" value=\"Stopp Køen\">\n" +
                    "<button class=\"btn btn-sm btn-primary\"  onclick=\"settIKo(" + delEmne.getNr() +")>Still i k&oslash;</button>\n");
        }else{
            out.println("<input type=\"button\" onclick=\"startStoppKoe(" + delEmne.getNr() +")\" class=\"btn btn-sm btn-success\" id=\"startKoe\" value=\"Start Køen\">\n");
        }
    %>
    <table class="table table-hover" id="minTable">
        <thead>
        <tr>
            <th>Tid</th>
            <th>Navn</th>
            <th>Kommentar</th>
            <th>Sitteplass</th>
            <th></th>
        </tr>
        </thead>


        <tbody>
        <c:forEach var="gruppe" items="$">
            <tr>
                <td>gruppe.tid</td>
                <td>gruppe.leder</td>
                <td>1, 2, 3</td>
                <td>Labben, Bord 13</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-primary" data-task="choose" title="Velg"
                                onclick="location.href='godkjennOving.htm'"><i class="glyphicon glyphicon-edit"></i>
                        </button>
                        <button class="btn btn-warning" data-task="edit" title="Endre &oslash;vinger"
                                onclick="endreBruker()"><i class="glyphicon glyphicon-edit"></i>
                        </button>
                        <button class="btn btn-danger" data-task="remove" title="Fjern"
                                onclick="slettBruker()"><i class="glyphicon glyphicon-remove"></i>
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="<c:url value="/resources/js/koen.js"/>"></script>
