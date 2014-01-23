<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="table table-hover" >
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
            <c:forEach var="koegrupper" items="${grupper}" varStatus="status">
                <tr
                        <c:if test="${koegrupper.faarHjelp!=null}">
                            class="success">
                            <td><a class="faarHjelpKnapp btn btn-success btn-sm " data-placement="top" data-toggle="popover"
                                   title="" data-content="<c:out value="${status}"/> navn"
                                   data-original-title="Får hjelp av"><i class="glyphicon glyphicon-eye-open"></i> </a></td>
                        </c:if>
                        <c:if test="${koegrupper.faarHjelp==null}">
                            >
                            <td><c:out value="${koegrupper.klokkeslett}"/></td>
                        </c:if>
                <td><c:out value="${koegrupper.medlemmer[0].fornavn}"/> <c:out
                        value="${koegrupper.medlemmer[0].etternavn}"/></td>
                <td><c:out value="${koegrupper.kommentar}"/></td>
                <td><c:out value="${koegrupper.sitteplass}"/>, bord <c:out value="${koegrupper.bordnr}"/></td>
                <td>
                    <div class="btn-group" id="<c:out value="${koegrupper.gruppeID}"/>">

                        <button class="btn btn-primary" data-task="choose" title="Velg" id="${koegrupper.koe_id}:${koegrupper.gruppeID}"
                                onclick="velgGruppeFraKoe(this.id)"><i class="glyphicon glyphicon-edit"></i>
                        </button>
                        <button class="btn btn-warning" data-task="edit" title="Endre &oslash;vinger"
                                onclick="endreBruker(this.parentNode.id)"><i class="glyphicon glyphicon-edit"></i>
                        </button>
                        <button class="btn btn-danger" data-task="remove" title="Fjern"
                                onclick="slettBruker(this.parentNode.id)"><i class="glyphicon glyphicon-remove"></i>
                        </button>
                    </div>
                </td>
                </tr>
            </c:forEach>
            </tbody>
</table>