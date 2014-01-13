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
    <datalist id="treff">
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

        <tbody>

        <tr>
            <td>Olve Andr?</td>
            <td>B?rmark</td>
            <td>oabormar@stud.hist.no</td>
            <td>
                <div class="btn btn-group">
                    <button type="remove" class="btn btn-danger" data-task="remove" title="Fjern"
                            onclick="fjernBruker()"><i class="glyphicon glyphicon-remove"></i>
                    </button>
                </div>
            </td>
        </tr>

        <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">
        <tr>
            <td><c:out value="${bruker.fornavn}"/></td>
            <td><c:out value="${bruker.etternavn}"/></td>
            <td><c:out value="${bruker.mail}"/></td>
            <td><button type="remove" id="${status.index}" class="btn btn-danger" data-task="remove" title="Fjern"
                        onclick="fjernBruker()"><i class="glyphicon glyphicon-remove"></i>
            </button></td>
        </tr>
        </c:forEach>

        </tbody>
    </table>


    <select class="form-control" id="opValg" onchange="bestemOperasjon()">
        <option value="studass">Gj?r til studentassistent</option>
        <option value="leggInnFag">Legg til nytt fag</option>
        <option value="fjernFag">Fjern fag</option>
    </select>

    <select id="fagValg" class="form-control">
        <option value="fag1">Fag1</option>
        <option value="fag2">Fag2</option>
        <option value="fag3">Fag3</option>
    </select>
</div>