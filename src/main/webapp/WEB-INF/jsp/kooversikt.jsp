<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 10.01.14
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-8">
    <h3>
        <div id="operasjonstekst">
            K&oslash; for (Emne)
        </div>
    </h3>
        <a href="<c:url value="settIKo.htm"/>">
            <button class="btn btn-sm btn-primary">Still i k&oslash;</button>
        </a>
    <button class="btn btn-sm btn-success" onclick="statusKoe()" id="koStatus" value="stopp">Start Køen</button>

    <table class="table table-hover" id="minTable">
        <thead>
        <tr>
            <th>Tid</th>
            <th>Navn</th>
            <th>Kommentar</th>
            <th>Sitteplass</th>
            <th></th>
        </tr>
        </thead>


        <tbody>

        <tr>
            <td>1:20</td>
            <td>B&oslash;rmark, Olve André</td>
            <td>1, 2, 3</td>
            <td>Labben, Bord 13</td>
            <td>
                <div class="btn-group">
                    <button type="choose" class="btn btn-primary" data-task="choose" title="Velg"
                            onclick="endreBruker()"><i class="glyphicon glyphicon-edit"></i>
                    </button>
                    <button type="edit" class="btn btn-warning" data-task="edit" title="Endre &oslash;vinger"
                            onclick="endreBruker()"><i class="glyphicon glyphicon-edit"></i>
                    </button>
                    <button type="remove" class="btn btn-danger" data-task="remove" title="Fjern"
                            onclick="slettBruker()"><i class="glyphicon glyphicon-remove"></i>
                    </button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script src="<c:url value="/resources/js/koen.js"/>"></script>
