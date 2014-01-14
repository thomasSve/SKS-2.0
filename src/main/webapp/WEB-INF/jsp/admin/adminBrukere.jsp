<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="col-md-8">
    <h2>Administrer brukere</h2>
    <%--Søkefunksjon etter brukere--%>
    <form class="søkbar" role="search" action="search" method="POST">
        <div class="input-group">

            <input type="text" class="form-control" placeholder="Søk" name="srch-term" id="srch-term">

            <div class="input-group-btn">
                <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>

        </div>
    </form>
    <div class="span5">
        <table class="table table-hover" id="minTable">
            <thead>
            <tr>
                <th class="header">Fornavn</th>
                <th class="header">Etternavn</th>
                <th class="header">Epost</th>
                <th class="header">Rettighet</th>
                <th class="header">Opprettet</th>
                <th class="header">Status</th>
                <th class="header"></th>
            </tr>
            </thead>

            <tbody>

            <tr>
                <td>Olve</td>
                <td>Børmark</td>
                <td>oabormar@stud.hist.no</td>
                <td>Student</td>
                <td>2012/08/16</td>
                <td><span class="btn btn-success btn-sm active">Aktiv</span></td>
                <td>
                    <div class="input-group-btn">
                        <button type="edit" class="btn btn-warning btn-sm" data-toggle="modal"
                                data-target="#endrebrukerModal" title="Endre">
                            <i class="glyphicon glyphicon-edit"></i></button>
                        <button type="remove" class="btn btn-danger btn-sm" data-task="remove" title="Fjern"
                                onclick="slettBruker()" id="removeknapp"><i class="glyphicon glyphicon-remove"></i>
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>


    <%--Legg til bruker funkjson--%>
    <form:form method="POST" modelAttribute="bruker" action="leggTilBruker.htm">
        <h2>
            <div id="operasjonstekst">
                Legg til bruker
            </div>
        </h2>
        <div class="form-group">
            <label for="fornavn">Fornavn</label>

            <form:input path="fornavn" id="fornavn" class="form-control"/>

            <form:errors path="fornavn"/>
        </div>
        <div class="form-group">
            <label for="etternavn">Etternavn:</label>

            <form:input id="etternavn" path="etternavn" class="form-control"/>

            <form:errors path="etternavn"/>
        </div>


        <div class="form-group">
            <label for="mail">Epost</label>

            <form:input id="mail" path="mail" class="form-control"/>

            <form:errors path="mail"/>
        </div>
        <div class="form-group">
            <label>Passord:</label>

            <form:password path="passord" class="form-control"/>

            <form:errors path="passord"/>
        </div>

        <div class="form-group">
            <label for="rettighet">Rettigheter</label>
            <form:select id="rettighet" class="form-control" path="rettighet">
                <form:option value="0"><i>Ingen valgt</i></form:option>
                <form:option value="1">Admin</form:option>
                <form:option value="2">Foreleser</form:option>
                <form:option value="3">Student</form:option>
            </form:select>
        </div>
        <input type="submit" class="btn btn-primary btn-block">
        </input>
    </form:form>
    <br>


    <form method="POST" modelAttribute="leggTilViaFIl" action="leggTilFil.html">
        <div id="leggTilFilText">
            <h2> Legg til flere brukere via fil </h2>
        </div>
        <input type="file" id="minFil">
        <output id="filInfo"></output>
        <script>
            document.getElementById('minFil').addEventListener('change',
                    handleFileSelect, false);
        </script>
        <br>
        <button type="button" class="btn btn-primary btn-block">Last opp fil</button>
    </form>

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
                            <label for="endretfornavn">Fornavn</label>

                            <input path="endrefornavn" id="endrefornavn" class="form-control"/>

                            <errors path="fornavn"/>
                        </div>
                        <div class="form-group">
                            <label for="endreetternavn">Etternavn:</label>

                            <input id="endreetternavn" path="etternavn" class="form-control"/>

                            <errors path="etternavn"/>
                        </div>

                        <fieldset disabled>
                            <div class="form-group">
                                <label for="endreepost">Epost</label>

                                <input id="endreepost" path="epost" class="form-control"/>

                                <errors path="epost"/>
                            </div>
                            <div class="form-group">
                                <label>Passord:</label>

                                <input path="passord" class="form-control"/>

                                <errors path="passord"/>
                            </div>
                        </fieldset>

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
</div>