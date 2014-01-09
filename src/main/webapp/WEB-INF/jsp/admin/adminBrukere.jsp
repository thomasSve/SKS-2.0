<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4">
    <h2>Administrer brukere</h2>

    <form class="søkbar" role="search">
        <div class="input-group">

            <input type="text" class="form-control" placeholder="Søk" name="srch-term" id="srch-term">

            <div class="input-group-btn">
                <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>

        </div>
    </form>
    <table class="table table-hover" id="minTable">
        <thead>
        <tr>
            <th class="header">Fornavn</th>
            <th class="header">Etternavn</th>
            <th class="header">Epost</th>
            <th class="header">Rettighet</th>
            <th class="header"></th>
        </tr>
        </thead>

        <tbody>

        <tr>
            <td>Olve André</td>
            <td>Børmark</td>
            <td>oabormar@stud.hist.no</td>
            <td>Student</td>
            <div class="input-group">
                <td>
                    <div class="input-group-btn">
                        <button type="edit" class="btn btn-warning" data-task="edit" title="Endre" onclick="endreBruker()" ><i class="glyphicon glyphicon-edit"></i></button>
                        <button type="remove" class="btn btn-danger" data-task="remove" title="Fjern" onclick="slettBruker()" id="removeknapp"><i class="glyphicon glyphicon-remove"></i></button>
                    </div>
                </td>
            </div>
        </tr>

        <tr>
            <td>Ola</td>
            <td>Nordmann</td>
            <td>onordmann@stud.hist.no</td>
            <td>Admin</td>
            <div class="input-group">
                <td>
                    <div class="input-group-btn">
                        <button type="edit" class="btn btn-warning" title="Endre" onclick="endreBruker()" ><i class="glyphicon glyphicon-edit"></i></button>
                        <button type="remove" class="btn btn-danger" title="Fjern" onclick="slettBruker()"><i class="glyphicon glyphicon-remove"></i></button>
                    </div>
                </td>
            </div>
        </tr>

        </tbody>
    </table>

    <form method="POST" modelAttribute="leggTilBruker" action="leggtilbruker.html">
        <h2>
            <div id="operasjonstekst">
                Legg til bruker
            </div>
        </h2>
        <div class="form-group">
            <label for="fornavn">Fornavn</label>

            <input path="fornavn" id="fornavn" class="form-control"/>

            <errors path="fornavn"/>
        </div>
        <div class="form-group">
            <label for="etternavn">Etternavn:</label>

            <input id="etternavn" path="etternavn" class="form-control"/>

            <errors path="etternavn"/>
        </div>


        <div class="form-group">
            <label for="epost">Epost</label>

            <input id="epost" path="epost" class="form-control"/>

            <errors path="epost"/>
        </div>
        <div class="form-group">
            <label>Passord:</label>

            <input path="passord" class="form-control"/>

            <errors path="passord"/>
        </div>

        <div class="form-group">
            <label for="rettigheter">Rettigheter</label>
            <select id="rettigheter" class="form-control">
                <option value="ingen"><i>Ingen valgt</i></option>
                <option value="admin">Admin</option>
                <option value="lærer">L&aeligrer</option>
                <option value="studentassistent">Studentassistent</option>
                <option value="student">Student</option>
            </select>
        </div>
        <button type="button" class="btn btn-primary btn-block"><div id="leggtilknapp">Legg til</div></button>
    </form>
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
    <br>
</div>
<script>
    function handleFileSelect(hendelse) {
        var minFil = hendelse.target.files[0];
        infoStreng = '<ul><li>Navn: ' + minFil.name + '</li>' +
                '<li>Størrelse: ' + minFil.size + 'bytes</li>' +
                '<li>Type: ' + minFil.type + '</li></ul>';

        document.getElementById('filInfo').innerHTML = infoStreng;
    }
    /*
     <fieldset disabled><fieldset disabled>
     <fieldset disabled>
     <fieldset disabled>
     <fieldset disabled>

     */
    function endreBruker(valgt) {
        if (document.getElementsByTagName("edit").value !== "ingen") {
            document.getElementById("operasjonstekst").innerHTML = "Endre bruker";
            document.getElementById("leggtilknapp").innerHTML = "Lagre endring";

        }
        else {
            document.getElementById("operasjonstekst").innerHTML = "Legg til bruker";
            document.getElementById("leggtilknapp").innerHTML = "Legg til";
        }
    }


    function slettBruker(valgt)
    {
        if(typeof(valgt) == "object"){
            $(valgt).closest("tr").remove();
            alert("Slett bruker?")
        } else {
            return false;
        }
    }
</script>