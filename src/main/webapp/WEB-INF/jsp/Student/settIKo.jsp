<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-md-4">
    <h1>Sett deg i kø </h1>

    <form action="regKoe" modelAttribute="koeBruker" method="post" role="form">
        <div class="form-group">
            <label for="sitteplass">Sitteplass:</label>
            <select class="form-control" name="Sitteplass" id="sitteplass" path="plassering">
                <option value="labben">Labben</option>
            </select>
        </div>
        <div class="form-group">
            <label for="bordnr">Bordnr:</label>
            <select class="form-control" name="Bord" id="bordnr" path="plassering">
                <option value="labben">13</option>
            </select>
        </div>
        <div class="form-group">
            <label for="ovingnr">Øving:</label>
            <select class="form-control" name="Øving" id="ovingnr" path="ovingsnr">
                <option value="labben">1</option>
            </select>
        </div>
        <div class="form-group">
            <select multiple="true" class="form-control" path="flereOvinger">
                <%-- MÅ VÆRE EN FOR-LØKKE SOM GÅR GJENNOM ALLE ØVINGER FOR HVERT ENKELT FAG--%>
                <option value="Oving1">Oving1</option>
                <option value="Oving2">Oving2</option>
                <option value="Oving3">Oving3</option>
                <option value="Oving1">Oving1</option>
                <option value="Oving2">Oving2</option>
                <option value="Oving3">Oving3</option>
                <option value="Oving1">Oving1</option>
                <option value="Oving2">Oving2</option>
                <option value="Oving3">Oving3</option>
            </select>
        </div>


        <div class="form-group">
            <label for="beskrivelse">Beskrivelse</label>
            <input class="form-control" type="text" id="beskrivelse" path="beskrivelse">
        </div>
        <!--
        Hvis noen skal settes i gruppe her?
        -->
        <div class="form-group">
            <label for="grupper">Gruppe?</label>
            <select id="grupper" class="form-control">
                <option>Hvem er du i gruppe med?</option>
                
            </select>
        </div>
        <button type="submit" id="leggTil" class="btn btn-md btn-primary">Legg til i kø</button>
    </form>
</div>