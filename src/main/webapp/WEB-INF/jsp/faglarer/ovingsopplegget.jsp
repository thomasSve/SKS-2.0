<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-8">
    <h1>Sett regler for &oslash;vingsopplegget</h1>

    <form>
        <h3>Grunnleggende</h3>

        <div class="form-group">
            <label for="tot">Totalt antall &oslash;vinger:</label>
            <input class="form-control" type="number" id="tot" value="1" min="1" oninput="settMaxKrav()">
        </div>

        <div class="form-group">
            <label for="ant">Minimum godkjente:</label>
            <input type="number" id="ant" value="1" min="0" max="1" class="form-control">
        </div>


        <h3>Avansert (obligatoriske arbeidskrav)</h3>

        <form>

            <input type="radio" name="visMer" value="aktiver" id="visMerAkt" onclick="utvid();">
            <lable for="visMerAkt">Aktiver</lable>
            <input type="radio" name="visMer" value="deaktiver" id="visMerDAkt" onclick="utvid();" checked>
            <lable for="visMerDAkt">Deaktiver</lable>
        </form>

        <div id="visMer" style="display:none">
            <hr>

            <h4>Sett opp reglement for &oslash;vingssystemet.</h4>

            <form>
                <h3>&Oslash;vinger</h3>


                <div class="form-group">
                    <select multiple id="ovingValg" onchange="settMaxKrav2()" class="form-control">
                        <option value="oving1" selected="selected">oving1</option>
                        <option value="oving2">oving2</option>
                        <option value="oving3">oving3</option>
                        <option value="oving4">henter ofc alle i DB</option>
                    </select>
                    <h3>Minst godkjente</h3>

                    <input class="form-control" type="number" id="antGodkjente" value="1" min="1">
                    <input class="btn" type="button" value="Legg til regel" onclick="leggTilRegel()">
                </div>


            </form>
            <p><i>Sl&aring; sammen flere ævinger ved &aring; hold inne "CTRL"-tasten mens du
                trykker p&aring; &oslash;nsket &oslash;ving,<br>eller hold inn "SHIFT"-tasten for &aring; automatisk velge alle &oslash;vingene til og
                med
                den
                du trykker p&aring;.<br>Trykk deretter "legg til". Flere regler kan legges til etterp&aring;.</i>
            </p>

            <h3>Innlagte spesialregler</h3>

            <div id="regler"><i>ingen</i></div>
        </div>
        <div id="test"></div>

        <input class="btn btn-primary btn-block" type="submit" value="Lagre reglement">
    </form>
</div>
<script src="<c:url value="/resources/js/ovingsopplegg.js"/>"></script>

