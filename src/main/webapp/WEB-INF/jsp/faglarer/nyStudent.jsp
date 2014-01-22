<%--
  Created by IntelliJ IDEA.
  User: Kjetil
  Date: 09.01.14
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<form:form method="POST" modelAttribute="nyStudent" action="leggtilbruker.html">
    <h2>Legg til bruker</h2>

    <div class="form-group">
        <label for="fornavn">Fornavn</label>
        <form:input path="fornavn" id="fornavn" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="etternavn">Etternavn:</label>
        <form:input path="etternavn" id="etternavn" class="form-control"/>
    </div>


    <div class="form-group">
        <label for="epost">Epost</label>
        <form:input path="mail" type="email" id="epost" class="form-control"/>
    </div>

    <div class="form-group">
        <label for="passordfelt">Passord:</label>
        <form:input path="passord" class="form-control" id="passordfelt"/>
    </div>

    <div class="form-group">
        <label for="emne">Emner</label>
        <form:select id="emne" path="" class="form-control">
            <form:option value="hei"></form:option>
        </form:select>
    </div>
    <jsp:setProperty name="nyStudent" property="rettighet" value="3"></jsp:setProperty>
    <jsp:setProperty name="nyStudent" property="aktiv" value="1"></jsp:setProperty>

    <input type="submit" class="btn btn-primary btn-block">Legg til</input>
</form:form>
