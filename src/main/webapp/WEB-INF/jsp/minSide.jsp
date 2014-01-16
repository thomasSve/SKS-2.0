<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-10">
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
            <jsp:include page="populerMinSide.jsp" />
            </tbody>
        </table>
    </div>
</div>
</div>


