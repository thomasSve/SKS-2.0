<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8">
    <h3><u>Mine Fag</u></h3>
<div class="row">
    <div class=" table-responsive">

        <table class="col-lg-10 table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Emnekode</th>
                <th>Emnenavn</th>
                <th>&Oslash;vingsoversikt</th>
            </tr>
            </thead>
            <tbody>

            <form onsubmit="mysubmit()" method="POST" action="koOversikt.htm">
                <input type="hidden" name="hiddenKoe" id="hiddenKoe" />
                <input type="hidden" name="hiddenEmneNavn" id="hiddenEmneNavn" />
                <jsp:include page="populerMinSide.jsp" />
            </form>
            </tbody>
        </table>
    </div>
</div>
</div>
<script src="<c:url value="/resources/js/koen.js"/>"></script>

