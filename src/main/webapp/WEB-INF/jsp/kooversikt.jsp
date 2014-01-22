<%@ page import="no.hist.tdat.javabeans.DelEmne" %>
<%@ page import="no.hist.tdat.javabeans.Koe" %>
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
    <%
        DelEmne delEmne = (DelEmne) request.getAttribute("delEmne");
        int koe_id = delEmne.getKoe_id();
        if (delEmne.isKoe_status()) {

            out.println("<input type=\"button\" onclick=\"startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-danger\" id=\"stoppKoe\" value=\"Stopp Køen\">\n" +
                    "<button class=\"btn btn-sm btn-primary\"  onclick=\"settIKo(" + koe_id + ")\">Still i k&oslash;</button>");
        } else {
            out.println("<input type=\"button\" onclick=\"startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-success\" id=\"startKoe\" value=\"Start Køen\">\n");
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
        <c:forEach var="koegrupper" items="${grupper}" varStatus="status">
            <tr
                    <c:if test="${koegrupper.faarHjelp!=null}">
                        class="success">
                        <td><a class="faarHjelpKnapp btn btn-success btn-sm " data-placement="top" data-toggle="popover"
                               title="" data-content="<c:out value="${status}"/> navn"
                               data-original-title="Får hjelp av"><i class="glyphicon glyphicon-eye-open"></i> </a></td>
                    </c:if>
                    <c:if test="${koegrupper.faarHjelp==null}">
                        >
                        <td><c:out value="${koegrupper.klokkeslett}"/></td>
                    </c:if>
            <td><c:out value="${koegrupper.medlemmer[0].fornavn}"/> <c:out
                    value="${koegrupper.medlemmer[0].etternavn}"/></td>
            <td><c:out value="${koegrupper.kommentar}"/></td>
            <td><c:out value="${koegrupper.sitteplass}"/>, bord <c:out value="${koegrupper.bordnr}"/></td>
            <td>
                <div class="btn-group" id="<c:out value="${koegrupper.gruppeID}"/>">
                    <button class="btn btn-primary" data-task="choose" title="Velg" id="${koegrupper.koe_id}:${koegrupper.gruppeID}"
                            onclick="velgGruppeFraKoe(this.id)"><i class="glyphicon glyphicon-edit"></i>
                    </button>
                    <button class="btn btn-warning" data-task="edit" title="Endre &oslash;vinger"
                            onclick="endreBruker(this.parentNode.id)"><i class="glyphicon glyphicon-edit"></i>
                    </button>
                    <button class="btn btn-danger" data-task="remove" title="Fjern"
                            onclick="slettBruker(this.parentNode.id)"><i class="glyphicon glyphicon-remove"></i>
                    </button>
                </div>
            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="<c:url value="/resources/js/koen.js"/>"></script>
