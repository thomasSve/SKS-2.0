<%--
  vimCnett
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-4">
    <h1>Administrere studenter</h1>

    <form:form class="searchbar" role="search" action="leggTilStudentListe" modelAttribute="personerBeans">
        <div class="input-group">
            <input type="text" name="soketekst" class="form-control" placeholder="Søk" id="srch-term"/>

            <div class="input-group-btn">
                <input type="submit" class="btn" id="leggTil" value="Legg til"/>
            </div>
        </div>
    </form:form>


    <!--    KAN GJØRES FANCY MED LIVE UPDATE MED SØKETREFF!
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

        <form:form action="fjernStudent" method="post" modelAttribute="personerBeans">

            <tbody>
            <tr>
                <td>Olve Andr?</td>
                <td>B?rmark</td>
                <td>oabormar@stud.hist.no</td>
                <td>
                    <div class="btn btn-group">
                        <input type="submit" name="0" value="Slett" class="btn btn-danger" data-task="remove" title="Fjern"><i
                                class="glyphicon glyphicon-remove"></i>
                        </input>
                    </div>
                </td>
            </tr>

            <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">
                <tr>
                    <td><c:out value="${bruker.fornavn}"/></td>
                    <td><c:out value="${bruker.etternavn}"/></td>
                    <td><c:out value="${bruker.mail}"/></td>
                    <td>
                        <input type="submit" class="btn btn-danger" data-task="remove"
                                title="Fjern" name="${status.index}"><i class="glyphicon glyphicon-remove"></i>
                        </input>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </form:form>
    </table>


    <form:form action="bekreftelse" method="post" modelAttribute="personerBeans">
        <select class="form-control" name="opValg">
            <option value="studass">Gjør til studentassistent</option>
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