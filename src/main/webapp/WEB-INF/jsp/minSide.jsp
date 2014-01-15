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
            <%     /*           //TODO Ted
                    Bruker denne = (Bruker) request.getSession().getAttribute("innloggetBruker");
                     for(int i = 0; i<denne.getEmne().size();i++){
                        out.println("<tr ><td>"+i+"</td><td>"+denne.getEmne().get(i).getEmneKode()+"</td><td><button class='btn btn-md btn-primary btn-block'>"+denne.getEmner().get(i).getEmneNavn()+"</button></td><td>");
                        for(int a=0;a<denne.getEmne().get(i).getStudentovinger().size();a++){
                            if(denne.getEmne().get(i).getStudentovinger().get(a).isGodkjent()){
                                String godkjentInfo = "Godkjent av:\t "+denne.getEmne().get(i).getStudentovinger().get(a).getGodkjentAv()+"\ndato:\t\t"+denne.getEmner().get(i).getStudentovinger().get(a).getGodkjentTid();
                                out.println("<a title='"+godkjentInfo+"' style='color:green'>"+denne.getEmne().get(i).getStudentovinger().get(a).getOvingnr()+"</a>");
                            }else{
                                out.println("<a>"+denne.getEmne().get(i).getStudentovinger().get(a).getOvingnr()+"</a>");
                            }
                        }*/
                        out.print("</td><td>(?)</td></tr>");
                }
            %>
        </tbody>
    </table>
       </div>

    </div>

</div>



