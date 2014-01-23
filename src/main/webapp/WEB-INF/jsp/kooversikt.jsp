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
<div class="pull-left col-md-8" onload="sjekkAktivKoe(${delEmne.koe_status});">

    <h3>
        <div id="operasjonstekst">
            K&oslash; for (<c:out value="${delEmne.delEmneNavn}"/>
        </div>
    </h3>
    <div class="input-group">
        <form action="settIKo.htm" onsubmit="mysubmit()" method="POST">

            <input type="hidden" name="emneNr" id="emneNr"/>
            <input type="hidden" name="delemneNr" id="delemneNr"/>
            <%--<div class="btn-group">--%>
            <input type="submit" class="btn btn-sm btn-primary" id="stillIKo"
                   onclick="delemnenr=${delEmneIndex};emnenr=${emneIndex}" value="Still i k&oslash;">
            <%--</div>--%>
        </form>
        <div id="startStoppKoe">
            <%
                DelEmne delEmne = (DelEmne) request.getAttribute("delEmne");
                int koe_id = delEmne.getKoe_id();
                if (delEmne.isKoe_status()) {

                    out.println("<input type=\"button\" onclick=\"startStoppKoeKnapp();startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-danger\" id=\"stoppKoe\" value=\"Stopp Køen\">\n"
                            // + "<button class=\"btn btn-sm btn-primary\"  onclick=\"settIKo(" + koe_id + ")\">Still i k&oslash;</button>"
                    );
                } else {
                    out.println("<input type=\"button\" onclick=\"startStoppKoeKnapp();startStoppKoe(" + koe_id + ")\" class=\"btn btn-sm btn-success\" id=\"startKoe\" value=\"Start Køen\">\n");
                }
            %>
        </div>
    </div>
    <div id="koetabell">
        <tbody>
        <jsp:include page="oppdaterKoe.jsp"/>
    </div>
</div>
<script src="<c:url value="/resources/js/koen.js"/>"></script>
<script src="<c:url value="/resources/js/oppdaterKoe.js"/>"></script>
