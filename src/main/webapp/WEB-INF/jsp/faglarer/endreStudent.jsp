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
                <input class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"></i>
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
                <h2 class="modal-title">Endre student</h2>
            </div>
            <div class="modal-body">

                <table class="table table-condensed table-hover" id="table">
                    <thead>
                    <tr>
                        <th class="header">Fornavn</th>
                        <th class="header">Etternavn</th>
                        <th class="header">Epost</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td><c:out value="${valgtBruker.fornavn}"/></td>
                        <td><c:out value="${valgtBruker.etternavn}"/></td>
                        <td><c:out value="${valgtBruker.mail}"/></td>
                    </tr>
                        <c:forEach var="bruker" items="${personerBeans.valgtBruker}" varStatus="status">
                            <tr>
                                <td><c:out value="${bruker.fornavn}"/></td>
                                <td><c:out value="${bruker.etternavn}"/></td>
                                <td><c:out value="${bruker.mail}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <form method="POST" modelAttribute="leggTilBruker" action="leggtilbruker.html">
                    <div class="form-group">
                        <label for="endrefornavn">Fornavn</label>

                        <input path="endrefornavn" id="endrefornavn" class="form-control"/>

                        <errors path="fornavn"/>
                    </div>
                    <div class="form-group">
                        <label for="endreetternavn">Etternavn:</label>

                        <input id="endreetternavn" path="etternavn" class="form-control"/>

                        <errors path="etternavn"/>
                    </div>

                    <div class="form-group">
                        <label for="endreepost">Epost</label>

                        <input id="endreepost" path="mail" class="form-control"/>

                        <errors path="mail"/>
                    </div>

                    <div class="form-group">
                        <label for="endrerettigheter">Rettigheter</label>
                        <select id="endrerettigheter" class="form-control">
                            <option value="ingen"><i>Ingen valgt</i></option>
                            <option value="admin">Admin</option>
                            <option value="lærer">L&aeligrer</option>
                            <option value="studentassistent">Studentassistent</option>
                            <option value="student">Student</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="endrestatus">Status</label>
                        <select id="endrestatus" class="form-control">
                            <option value="ingen"><i>Aktiv</i></option>
                            <option value="admin">Inaktiv</option>
                        </select>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Lukk</button>
                <button type="button" class="btn btn-primary">Lagre</button>
            </div>
        </div>
    </div>
</div>
