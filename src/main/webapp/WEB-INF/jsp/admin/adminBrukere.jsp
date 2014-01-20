<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-md-8">
<ul class="nav nav-tabs nav-justified">
    <li id="lenkeEndre" class="active"><a href="#endre" data-toggle="tab">Adm. brukere</a></li>
    <li id="lenkeleggTilEnkelBruker"> <a href="#leggTilEnkelBruker"  data-toggle="tab">Legg til bruker</a></li>
    <li id="lenkebrukereViaFil"><a href="#brukereViaFil"  data-toggle="tab">Legg til via fil</a></li>
</ul>
<div class="tab-content">
    <p id="tabForms" hidden> ${tabForm} </p>

<div class="tab-pane fade in active" id="endre">
    <h2>Administrer brukere</h2>
    <%--Søkefunksjon etter brukere--%>
    <form:form class="søkbar" role="search" modelAttribute="personerBeans" action="search.htm" method="POST">
        <div class="input-group">

            <input type="text" class="form-control" placeholder="S&oslash;k" name="srch-term" id="srch-term">

            <div class="input-group-btn">
                <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"></i>
                </button>
            </div>

        </div>
        <div class="span5">
            <table class="table table-condensed table-hover" id="minTable">
                <thead>
                <tr>
                    <th class="header">Fornavn</th>
                    <th class="header">Etternavn</th>
                    <th class="header">Epost</th>
                    <th class="header col-sm-1">Status</th>
                    <th class="header col-sm-1"></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="random" items="${personerBeans.valgt}" varStatus="status">

                    <tr>
                        <td><c:out value="${bruker.fornavn}"/></td>
                        <td><c:out value="${bruker.etternavn}"/></td>
                        <td><c:out value="${bruker.mail}"/></td>
                        <td><c:if test="${bruker.aktiv == 1}"><span class="btn btn-success btn-sm active">&nbsp;Aktiv&nbsp;&nbsp;</span>
                        </c:if><c:if test="${bruker.aktiv == 0}"><span
                                class="btn btn-danger btn-sm disabled">Inaktiv</span>
                        </c:if></td>
                        <td>
                            <div class="input-group-btn">
                                <button type="edit" class="btn btn-warning btn-sm" data-toggle="modal"
                                        data-target="#endrebrukerModal" title="Endre">
                                    <i class="glyphicon glyphicon-edit"></i></button>
                                <button type="button" value="Slett" class="btn btn-danger btn-sm" data-task="remove"
                                        id="${bruker.mail}" onclick="slettBrukerFraKnapp(this.id)"
                                        title="Slett"><i class="glyphicon glyphicon-remove"></i>
                                </button>

                            </div>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <input type="hidden" name="tab" value="endre">
        </div>
    </form:form>
</div>
<div class="tab-pane fade" id="leggTilEnkelBruker">

    <%--Legg til bruker funkjson--%>
    <form:form method="POST" modelAttribute="bruker" action="leggTilBruker.htm">
        <h2>
            <div id="operasjonstekst">
                Legg til bruker
            </div>
        </h2>
        <p style="color: red"><strong>${melding}</strong></p>
        <div class="form-group">
            <label for="fornavn">Fornavn</label>

            <form:input path="fornavn" id="fornavn" class="form-control"/>

            <form:errors path="fornavn"/>
        </div>
        <div class="form-group">
            <label for="etternavn">Etternavn:</label>

            <form:input id="etternavn" path="etternavn" class="form-control"/>

            <form:errors path="etternavn"/>
            <form:hidden id="passord" path="passord" value="" class="form-control"/>
        </div>


        <div class="form-group">
            <label for="mail">Epost</label>

            <form:input id="mail" path="mail" class="form-control"/>

            <form:errors path="mail"/>
        </div>
        <form:hidden id="aktiv" path="aktiv" value="1" class="form-control"/>


        <div class="form-group">
            <label for="rettighet">Rettigheter</label>
            <form:select id="rettighet" class="form-control" path="rettighet">
                <form:option value="3">Student</form:option>
                <form:option value="2">Lærer</form:option>
                <form:option value="1">Admin</form:option>
            </form:select>
        </div>
        <br>
        <input type="hidden" name="tab" value="leggTilEnkelBruker">

        <input type="submit" id="leggtil" value="Legg til" class="btn btn-primary btn-block"/>

    </form:form>
</div>


    <div class="tab-pane fade" id="brukereViaFil">
        <form method="POST" action="leggTilFil.htm" id="lesInnFil">
            <div id="leggTilFilText">
                <h2> Legg til flere brukere via fil </h2>
            </div>

            <label for="files">Velg en fil: </label>
            <input id="files" type="file"/>

            <script>
                window.onload = function () {

                    //Sjekker om browseren har filstøtte
                    if (window.File && window.FileList && window.FileReader) {
                        var filesInput = document.getElementById("files");

                        filesInput.addEventListener("change", function (event) {

                            var files = event.target.files; //FileListe objekt
                            var output = document.getElementById("result");

                            for (var i = 0; i < files.length; i++) {
                                var file = files[i];

                                //Bare tekst
                                if (!file.type.match('plain')) continue;

                                var picReader = new FileReader();

                                picReader.addEventListener("load", function (event) {

                                    var textFile = event.target;

                                    var div = document.getElementById("newText");

                                    div.value = textFile.result;

                                });

                                //Leser tekstfil
                                picReader.readAsText(file);
                            }

                        });
                    }
                    else {
                        console.log("Nettleseren din støtter ikke Fil API");
                    }
                }
            </script>
            <input type="hidden" name="tab" value="brukereViaFil">
            <input type="hidden" name="newText" id="newText"/>
            <br>
            <button type="submit" class="btn btn-primary btn-block">Last opp fil</button>

        </form>
    </div>
</div>


<%--HVA er dette--%>
<div class="modal fade" id="endrebrukerModal" tabindex="-1" role="dialog" aria-labelledby="endrebrukerLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="velgøvingLabel">Endre Bruker</h2>
            </div>
            <div class="modal-body">
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

                    <div class="form-group form-control">
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
                    <input type="hidden" name="tab" value="leggTilEnkelBruker">l
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Lukk</button>
                <button type="button" class="btn btn-primary">Lagre</button>
            </div>
        </div>
    </div>
</div>
</div>


<script src="<c:url value="/resources/js/admin.js"/>"></script>

<%--JavaScriptet skal ligge her! og må til for å endre fane--%>
<script>
    var tabValue = String(document.getElementById("tabForms").innerHTML);
    if(tabValue.match(/endre/)){
        document.getElementById("endre").className = "tab-pane fade in active";
        document.getElementById("leggTilEnkelBruker").className = "tab-pane fade";
        document.getElementById("brukereViaFil").className = "tab-pane fade";

        document.getElementById("lenkeEndre").className="active";
        document.getElementById("lenkeleggTilEnkelBruker").className="";
        document.getElementById("lenkebrukereViaFil").className="";
    }else if(tabValue.match(/leggTilEnkelBruker/)){
        document.getElementById("endre").className = "tab-pane fade";
        document.getElementById("leggTilEnkelBruker").className = "tab-pane fade in active";
        document.getElementById("brukereViaFil").className = "tab-pane fade";

        document.getElementById("lenkeEndre").className="";
        document.getElementById("lenkeleggTilEnkelBruker").className="active";
        document.getElementById("lenkebrukereViaFil").className="";
    }else if(tabValue.match(/brukereViaFil/)){
        document.getElementById("endre").className = "tab-pane fade";
        document.getElementById("leggTilEnkelBruker").className = "tab-pane fade";
        document.getElementById("brukereViaFil").className = "tab-pane fade in active";

        document.getElementById("lenkeEndre").className="";
        document.getElementById("lenkeleggTilEnkelBruker").className="";
        document.getElementById("lenkebrukereViaFil").className="active";
    }
</script>

