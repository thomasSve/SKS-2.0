<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-10">
    <h3><u>Mine Fag</u></h3>

    <div class="span3 achievements-wrapper">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th>Emnekode</th>
            <th>Emnenavn</th>
            <th>&oslash;vingsoversikt</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%
                    Bruker denne = (Bruker) request.getSession().getAttribute("innloggetBruker");
                    for(int i = 0; i<denne.getEmner().size();i++){
                        out.println("<tr ><td>"+i+"</td><td>"+denne.getEmner().get(i).getEmneKode()+"</td><td><button class='btn btn-md btn-primary btn-block'>"+denne.getEmner().get(i).getEmneNavn()+"</button></td><td>1 2 3 4 5 6 7 8 9 10 11 12 13 14 15</td><td>(?)</td></tr>");
                }
            %>
        </tbody>
    </table>
       </div>

    </div>

</div>



