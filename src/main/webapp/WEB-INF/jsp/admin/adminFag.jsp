<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4">

    <h1>Administrer emne</h1>

    <h3>Endre emne</h3>
    <select  class="form-control" id="emnevalg" onchange="operasjon(this.value)">
        <option value="ingen"><i>Ingen valgt</i></option>
        <option value="emne2">Fag 2</option>
        <option value="emne3">Fag 3</option>
        <option value="emne4">Fag 4</option>
    </select>

    <form role="form">

        <h3>
            <div id="operasjonstekst">
                Lag nytt fag
            </div>
        </h3>
        <div class="form-group">
            <label for="emnekode">Emnekode:</label>
            <input  class="form-control" path="kode" id="emnekode" placeholder="Emnekode"/>
            <errors path="kode"/>
        </div>
        <div class="form-group">
            <label for="emnenamn">Emnenamn:</label>
            <input  class="form-control" id="emnenamn" placeholder="Emnenamn" path="navn"/>
            <errors path="navn"/>
        </div>
        <input class="btn btn-md btn-primary" type="submit" value="Lag fag" id="LagFag">
    </form>
</div>
