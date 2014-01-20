package no.hist.tdat.javabeans.beanservice;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.javabeans.DelEmne;
import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.Oving;
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
            delEmneList = databaseConnector.hentDelemner(bruker,tempEmne);
            tempEmne.setDelemner(delEmneList);
            for (int b = 0; b <delEmneList.size(); b++) {   //for hvert delemne pr emne
                DelEmne tempDelEmne = delEmneList.get(b);
                ovingList = databaseConnector.hentStudOvinger(bruker,tempEmne,tempDelEmne);
                tempDelEmne.setStudentovinger(ovingList);
            }
        }
    }

    public boolean endreKoeStatus(int koeId, int status){
        return databaseConnector.endreKoeStatus(koeId, status);
    }

    /**
     * Henter alle emner knyttet til en bruker (mail)
     *
     * @param mail, unik identifikator
     * @return liste over alle emner
     */
   public ArrayList<Emne> hentEmnerForStud(String mail) {
        return databaseConnector.hentEmnerForStud(mail);
    }

    public boolean leggTilEmne(Emne emne) throws org.springframework.dao.DuplicateKeyException{
        return databaseConnector.leggTilEmne(emne);
    }

}