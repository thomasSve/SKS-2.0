package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.*;
import no.hist.tdat.javabeans.Emne;
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
       bruker.setEmne(databaseConnector.hentMineEmner(bruker));
       ArrayList<Emne> emneList  = bruker.getEmne();                           //TODO Ted
       ArrayList<DelEmne> delEmneList;
        ArrayList<Oving> ovingList;
        for(int a=0;a<emneList.size();a++){ // For hvert emne i lista
            Emne tempEmne = emneList.get(a);
            delEmneList = databaseConnector.hentDelemner(tempEmne);
            tempEmne.setDelemner(delEmneList);
            for (int b = 0; b <delEmneList.size(); b++) {   //for hvert delemne pr emne
                DelEmne tempDelEmne = delEmneList.get(b);
                ovingList = databaseConnector.hentStudOvinger(bruker, tempDelEmne);
                tempDelEmne.setStudentovinger(ovingList);


            }
        }
    }
    public boolean endreKoeStatus(int koeId, int status){
        return databaseConnector.endreKoeStatus(koeId, status);
    }

}