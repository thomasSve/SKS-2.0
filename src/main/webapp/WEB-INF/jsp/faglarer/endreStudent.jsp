<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>

<div class="col-md-4">
    <h1>Administrere studenter</h1>

    <form class="searchbar" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Søk" name="srch-term" id="srch-term">

            <div class="input-group-btn">
                <button type="button" class="btn" id="leggTil">Legg til</button>
            </div>
        </div>
    </form>
    <datalist id="treff">
        <option value="ingen"></option>
    </datalist>
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
            <td>Olve Andr�</td>
            <td>B�rmark</td>
            <td>oabormar@stud.hist.no</td>
            <td>

            </td>
        </tr>
        <tr>
            <td>Ola</td>
            <td>Nordmann</td>
            <td>onordmann@stud.hist.no</td>
            <td>

            </td>
        </tr>

        </tbody>
    </table>


    <select class="form-control" id="opValg" onchange="bestemOperasjon()">
        <option value="studass">Gj�r til studentassistent</option>
        <option value="leggInnFag">Legg til nytt fag</option>
        <option value="fjernFag">Fjern fag</option>
    </select>

    <select id="fagValg" class="form-control">
        <option value="fag1">Fag1</option>
        <option value="fag2">Fag2</option>
        <option value="fag3">Fag3</option>
    </select>


    <script>
        function bestemOperasjon() {
            if (document.getElementById("opValg").value === "studass") {

            }
            else if (document.getElementById("opValg").value === "leggInnFag") {

            }
            else {

            }
        }
    </script>
</div>