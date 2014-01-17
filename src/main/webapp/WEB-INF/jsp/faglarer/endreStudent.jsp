<%@ page import="no.hist.tdat.javabeans.PersonerBeans" %>
<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
    function finnRettBruker(mail) {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {

        }
        xmlhttp.open("POST", "endreValgtBruker", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("brukerIndex=" + mail);

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //document.getElementById("calendar").innerHTML=xmlhttp.responseText;
            alert("jara");
        }
    }
</script>

<div class="col-md-8">

    <h2>Administrer studenter</h2>
    <%--Søkefunksjon etter brukere--%>
    <form:form class="søkbar" role="search" modelAttribute="personerBeans" action="leggTilStudentListe"
               method="POST">
        <div class="input-group">

            <input type="text" class="form-control" placeholder="Søk" name="soketekst" id="srch-term">

            <div class="input-group-btn">
                <input class="btn btn-success" value="Søk" type="submit"><i class="glyphicon glyphicon-search"></i>
                </input>
            </div>

        </div>

        <table class="table table-condensed table-hover" id="minTable">
            <thead>
            <tr>
                <th class="header">Fornavn</th>
                <th class="header">Etternavn</th>
                <th class="header">Epost</th>
                <th class="header"></th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">
                <tr>
                    <td><c:out value="${bruker.fornavn}"/></td>
                    <td><c:out value="${bruker.etternavn}"/></td>
                    <td><c:out value="${bruker.mail}"/></td>
                    <td>
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-warning btn-sm" id="${bruker.mail}"
                                    data-toggle="modal" onclick="finnRettBruker(this.id)"
                                    data-target="#endrebrukerModal" value="Endre" title="Endre">
                                <i class="glyphicon glyphicon-edit"></i></button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </form:form>
</div>


<div class="modal fade" id="endrebrukerModal" tabindex="-1" role="dialog" aria-labelledby="endrebrukerLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="velgøvingLabel">Endre Bruker</h2>
            </div>
            <div class="modal-body">

                <table class="table table-condensed table-hover" id="minTable">
                    <thead>
                    <tr>
                        <th class="header">Fornavn</th>
                        <th class="header">Etternavn</th>
                        <th class="header">Epost</th>
                    </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td><c:out value="${personerBeans.valgtBruker}"/></td>
                            <td><c:out value="${sessionScope.personerBeans.valgtBruker}"/></td>
                            <td><c:out value="${sessionScope.personerBeans}"/></td>
                        </tr>
                    </tbody>
                </table>


                <hr>

                <form:form action="gjorTilStudass" method="post">
                <div class="form-group">
                    <div class="form-group">
                        <label for="emner">Gjør til studentassistent i valgte fag:</label>
                        <select id="emner" class="form-control">
                            <option value="hei1">1</option>
                            <option value="hei2">2</option>

                            <c:forEach var="emne" items="${sessionScope.personerBeans.valgtBruker.emne}" varStatus="status">
                                <option value="${emne.emneKode}"><c:out value="${emne.emneKode}"/></option>
                            </c:forEach>

                        </select>
                    </div>
                    <input type="submit" value="Lagre"/>
                </div>
                </form:form>

                <hr>

                <form:form action="fjernFag" method="post">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="emner2">Fjern tilgang til valgte fag:</label>
                            <select id="emner2" class="form-control">

                            </select>
                        </div>
                        <input type="submit" value="Lagre"/>
                    </div>
                </form:form>

                <hr>


                <form:form action="leggTilFag" method="post">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="emner3">Gi tilgang til valgte fag:</label>
                            <select id="emner3" class="form-control">

                            </select>
                        </div>
                        <input type="submit" value="Lagre"/>
                    </div>
                </form:form>




            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Lukk</button>
                <button type="button" class="btn btn-primary">Lagre</button>
            </div>
        </div>
    </div>
</div>
</div>
