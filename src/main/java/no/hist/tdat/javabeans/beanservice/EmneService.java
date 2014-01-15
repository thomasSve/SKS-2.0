package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.Emner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmneService {

    @Qualifier("databaseConnector")
    @Autowired
    DatabaseConnector databaseConnector;




    public void hentEmner(Bruker bruker) {
       bruker.setEmner((ArrayList<Emner>)databaseConnector.hentMineEmner(bruker));
       ArrayList<Emner> tempList = bruker.getEmner();

        for (int i = 0; i < tempList.size(); i++) {
            //tempList.get(i).setStudentovinger(databaseConnector.hentStudOvinger(bruker)); //TODO fiks

        }
    }

}