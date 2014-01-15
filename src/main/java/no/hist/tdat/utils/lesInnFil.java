package no.hist.tdat.utils;

import no.hist.tdat.database.DatabaseConnector;
import no.hist.tdat.javabeans.Bruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Eirik on 10.01.14.
 */
@Component
public class lesInnFil {
    @Qualifier("databaseConnector")
    @Autowired
    DatabaseConnector databaseConnector;
    private String text;


    public void nyBruker(BufferedReader br)throws IOException {
        String s = "";
        String epost, fnavn, enavn = "";
        while((s = br.readLine()) != null){

            String[] str = s.split(",");
            epost = str[0];
            fnavn = str[1];
            enavn = str[2];
            System.out.println(epost+", "+fnavn+", "+enavn);
            databaseConnector.leggTilBruker(new Bruker(epost, Bruker.STUDENT_RETTIGHET, fnavn, enavn));
        }
    }

    public BufferedReader tekstFil(FileReader fil)throws IOException{
        return new BufferedReader(fil);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**public static void main(String[] args) {
        String sti = "C:/Users/Eirik/Dropbox/vimcnett/HenrietteEirik/brukere.txt";
        try{
        BufferedReader br = tekstFil(sti);
        nyBruker(br);
        }catch(IOException e){
            System.out.println(e);
        }

    } */

}
