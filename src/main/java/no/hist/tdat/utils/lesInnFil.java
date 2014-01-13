package no.hist.tdat.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Eirik on 10.01.14.
 */
public class lesInnFil {

    public static void nyBruker(BufferedReader br)throws IOException {
        String s = "";
        String epost = "";
        String fnavn = "";
        String enavn = "";
        while((s = br.readLine()) != null){

            String[] str = s.split(",");
            epost = str[0];
            fnavn = str[1];
            enavn = str[2];
            System.out.println(epost+ ", " +fnavn+ ", "+ enavn);
            //sql
        }

    }

    public static BufferedReader tekstFil(String sti)throws IOException{
        return new BufferedReader(new FileReader(sti));
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
