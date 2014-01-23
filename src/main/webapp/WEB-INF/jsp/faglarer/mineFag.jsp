  <!--
    --
    -- @author Jørgen
    --
    -->

<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8">
    <h3>Mine fag</h3>
    <div class="row">
        <div class=" table-responsive">
            <table class="col-lg-10 table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Emnekode</th>
                    <th>Emnenavn</th>
                </tr>
                </thead>
                <tbody>
                    <jsp:include page="hentEmneKnapper.jsp" />
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/emneAdmin.js"/>"></script>