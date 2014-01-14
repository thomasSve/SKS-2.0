<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 13.01.14
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6">
<h2>Godkjenn øvinger</h2>

<table class="table table-hover" id="minTable">
    <thead>
    <tr>
        <th>Fornavn</th>
        <th>Etternavn</th>
        <th>Øvinger</th>
        <th></th>
    </tr>
    </thead>


    <tbody>

    <tr>
        <td>Olve André</td>
        <td>Børmark</td>
        <td>1, 2, 3</td>
        <td>
            <div class="btn-group">
                <button type="edit" class="btn btn-warning" data-toggle="modal" data-target="#velgøvingModal"
                        data-task="edit" title="Endre øvinger"
                        onclick="endreBruker()"><i class="glyphicon glyphicon-edit"></i>
                </button>
                <button type="remove" class="btn btn-danger" data-task="remove" title="Fjern"
                        onclick="slettBruker()"><i class="glyphicon glyphicon-remove"></i>
                </button>
            </div>
        </td>
    </tr>
    <tr>
        <td>Ted</td>
        <td>Kristoffersen</td>
        <td>2</td>
        <td>
            <div class="btn-group">
                <button type="edit" class="btn btn-warning" data-toggle="modal" data-target="#velgøvingModal"
                        data-task="edit" title="Endre øvinger"
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

<div class="btn-group">
    <button type="godkjenn" class="btn btn-success"><i class="glyphicon glyphicon-ok"></i> Godkjenn</button>

    <button type="leggtil" class="btn btn-primary"><i class="glyphicon glyphicon-user"></i> Legg til studenter
    </button>
    <button type="endreØvinger" class="btn btn-info" data-toggle="modal" data-target="#velgøvingFellesModal"><i
            class="glyphicon glyphicon-plus"></i> Endre øvinger
    </button>
</div>

<div class="modal fade" id="velgøvingModal" tabindex="-1" role="dialog" aria-labelledby="velgøvingLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="velgøvingLabel">Velg øvinger</h4>
            </div>
            <div class="modal-body">

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseEn">
                                    &Oslash;ving 1
                                </a>
                            </h4>
                        </div>
                        <div id="collapseEn" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTo">
                                    &Oslash;ving 2
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTo" class="panel-collapse collapse">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTre">
                                    &Oslash;ving 3
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTre" class="panel-collapse collapse">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Lukk</button>
                <button type="button" class="btn btn-primary">Velg</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="velgøvingFellesModal" tabindex="-1" role="dialog" aria-labelledby="velgøvingLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="velgøvingFellesLabel">Velg øvinger</h4>
            </div>
            <div class="modal-body">

                <div class="panel-group" id="accordionFelles">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordionFelles" href="#collapseFellesEn">
                                    &Oslash;ving 1
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFellesEn" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                                <label class="checkbox">
                                    <input type="checkbox" value="person2">Ted Kristoffersen</input>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordionFelles" href="#collapseFellesTo">
                                    &Oslash;ving 2
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFellesTo" class="panel-collapse collapse">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                                <label class="checkbox">
                                    <input type="checkbox" value="person2">Ted Kristoffersen</input>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordionFelles" href="#collapseFellesTre">
                                    &Oslash;ving 3
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFellesTre" class="panel-collapse collapse">
                            <div class="panel-body">
                                <label class="checkbox">
                                    <input type="checkbox" value="person1">Olve André Børmark</input>
                                </label>
                                <label class="checkbox">
                                    <input type="checkbox" value="person2">Ted Kristoffersen</input>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Lukk</button>
                <button type="button" class="btn btn-primary">Velg</button>
            </div>
        </div>
    </div>
</div>
</div>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script>
