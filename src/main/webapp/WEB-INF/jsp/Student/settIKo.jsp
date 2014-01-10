<div class="col-md-4">
    <h1>Sett deg i k&oslash; </h1>

    <form role="form">
        <div class="form-group">
            <label for="sitteplass">Sitteplass:</label>
            <select class="form-control" name="Sitteplass" id="sitteplass">
                <option value="labben">Labben</option>
            </select>
        </div>
        <div class="form-group">
            <label for="bordnr">Bordnr:</label>
            <select class="form-control" name="Bord" id="bordnr">
                <option value="labben">13</option>
            </select>
        </div>


        <div class="form-group">
            <label for="beskrivelse">Beskrivelse</label>
            <input class="form-control" type="text" id="beskrivelse">
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
        <button type="submit" id="leggTil" class="btn btn-md btn-primary">Legg til i k&oslash;</button>
    </form>
</div>