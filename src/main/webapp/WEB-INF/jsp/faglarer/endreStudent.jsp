<%--
  vimCnett
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-4">
    <h1>Administrere studenter</h1>

    <form:form class="searchbar" role="search" action="leggTilStudentListe" modelAttribute="personerBeans">
        <div class="input-group">
            <input type="text" name="soketekst" class="form-control" placeholder="S�k" id="srch-term"/>

            <div class="input-group-btn">
                <input type="submit" class="btn" id="leggTil" value="Legg til"/>
            </div>
        </div>
    </form:form>


    <!--    KAN GJ�RES FANCY MED LIVE UPDATE MED S�KETREFF!
    <datalist id="treffHittil">
        <option value="ingen"></option>
    </datalist>
    -->
    <h3>Valgte student(-er)</h3>

    <table class="table table-hover">
        <thead>
        <tr>
            <th class="header">Fornavn</th>
            <th class="header">Etternavn</th>
            <th class="header">Epost</th>
            <th class="header"></th>
        </tr>
        </thead>

        <form:form action="fjernStudent" method="get" modelAttribute="personerBeans">

            <tbody>
                <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">

                    <tr>
                        <td><c:out value="${bruker.fornavn}"/></td>
                        <td><c:out value="${bruker.etternavn}"/></td>
                        <td><c:out value="${bruker.mail}"/></td>
                        <td>

                            <form:hidden path="index" value="${status.index}" name="index"></form:hidden>

                            <button type="submit" class="btn btn-danger btn-sm" data-task="remove" title="Fjern"
                                id="removeknapp"> <i class="glyphicon glyphicon-remove"></i>  </button>

                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </form:form>
    </table>


    <form:form action="bekreftelse" method="post" modelAttribute="personerBeans">
        <select class="form-control" name="opValg">
            <option value="studass">Gj�r til studentassistent</option>
            <option value="leggInnFag">Legg til nytt fag</option>
            <option value="fjernFag">Fjern fag</option>
        </select>

        <select id="fagValg" class="form-control">
            <c:forEach var="i" items="${personerBeans.fellesEmner}">
                <option value="<c:out value="${i}"/>"><c:out value="${i}"/></option>
            </c:forEach>
        </select>

        <input type="submit" value="Lagre">
    </form:form>
</div>