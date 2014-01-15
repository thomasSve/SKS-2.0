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



            <%
                    Bruker denne = (Bruker) request.getSession().getAttribute("innloggetBruker");
                     for(int i = 0; i<denne.getEmne().size();i++){
                        out.println("<tr ><td>"+i+"</td><td>"+denne.getEmne().get(i).getEmneKode()+"</td><td><button class='btn btn-md btn-primary btn-block'>"+denne.getEmne().get(i).getEmneNavn()+"</button></td></tr>");

                        for(int j = 0; j <denne.getEmne().get(i).getDelemner().size() ; j++) {

                            out.print("<tr><td></td><td></td><td>"+denne.getEmne().get(i).getDelemner().get(j).getDelEmneNavn()+"</td><td>");
                            for(int a=0;a<denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().size();a++){
                                if(denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).isGodkjent()){
                                    String godkjentInfo = "Godkjent av:\t "+denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getGodkjentAv()+"\ndato:\t\t"+denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getGodkjentTid();
                                    out.println("<button class ='btn btn-success btn-sm active' title='"+godkjentInfo+"'>"+denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getOvingnr()+"</button>");
                                }else{
                                    out.println("<button class ='btn btn-default btn-sm active' >"+denne.getEmne().get(i).getDelemner().get(j).getStudentovinger().get(a).getOvingnr()+"</button>");
                                }
                            }out.println("</td></tr>");
                        }

                }
            %>
        </tbody>
    </table>
       </div>

    </div>

</div>



