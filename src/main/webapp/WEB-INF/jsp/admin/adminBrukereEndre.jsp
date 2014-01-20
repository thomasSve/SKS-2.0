<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-md-6">
    <form:form method="POST" modelAttribute="bruker" action="redigerBrukerLagre.htm">
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
                <form:option value="-1">Velg rettighet</form:option>
                <form:option value="3">Student</form:option>
                <form:option value="2">Lærer</form:option>
                <form:option value="1">Admin</form:option>
            </form:select>
        </div>

        <div class="form-group">
            <label for="endrestatus">Status</label>
            <select id="endrestatus" class="form-control">
                <option value="1"><i>Aktiv</i></option>
                <option value="0">Inaktiv</option>
            </select>
        </div>

        <div class="modal-footer">
            <button type="button" id="${redigerBrukere.mail}" onclick="adminBrukereTilbake(this.id)" class="btn btn-danger col-md-5" data-dismiss="modal">Tilbake</button>
            <input type="submit" id="endreBruker" value="Endre bruker" style="float: right" class="btn btn-primary col-md-5"/>
        </div>
    </form:form>


</div>


<script src="<c:url value="/resources/js/admin.js"/>"></script>