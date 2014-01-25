<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-md-6">
    <h3>Rediger <strong>${redigerBrukere.etternavn}, ${redigerBrukere.fornavn}</strong></h3>
    <form:form method="POST" modelAttribute="bruker" action="redigerBrukerLagre.htm">
        <p style="color: red"><strong>${melding}</strong></p>
        <div class="form-group">
            <label for="endrefornavn">Fornavn</label>
            <form:input value="${redigerBrukere.fornavn}" path="fornavn" id="endrefornavn" class="form-control" />

            <errors path="fornavn"/>
        </div>
        <div class="form-group">
            <label for="endreetternavn">Etternavn:</label>

            <form:input value="${redigerBrukere.etternavn}" id="endreetternavn" path="etternavn" class="form-control"/>

            <errors path="etternavn"/>
        </div>

        <div class="form-group">
            <label for="endreepost">Epost</label>

            <form:input value="${redigerBrukere.mail}" id="endreepost" path="mail" class="form-control"/>

            <errors path="mail"/>
        </div>

        <div class="form-group">
            <label for="rettighet">Rettigheter</label>
            <form:select id="rettighet" class="form-control" path="rettighet">
                <option selected="selected" value="3">Student</option>
                <option value="2">Lærer</option>
                <option value="1">Admin</option>
            </form:select>
        </div>

        <div class="form-group">
            <label for="endrestatus">Status</label>
            <form:select path="aktiv" id="endrestatus" class="form-control" >
                <option value="1">Aktiv</option>
                <option value="0">Inaktiv</option>
            </form:select>
        </div>

        <input type="hidden" name="redigerMail" value="${redigerBrukere.mail}">
        <div class="modal-footer">
            <button type="button" id="${redigerBrukere.mail}" onclick="adminBrukereTilbake(this.id)" class="btn btn-danger col-md-5" data-dismiss="modal">Tilbake</button>
            <input type="submit" id="endreBruker" value="Endre bruker" style="float: right" class="btn btn-primary col-md-5"/>
        </div>
    </form:form>


</div>


<script src="<c:url value="/resources/js/admin.js"/>"></script>