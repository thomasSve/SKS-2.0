package no.hist.tdat.database;

import no.hist.tdat.javabeans.Bruker;

import java.sql.*;
import java.util.ArrayList;

/**
 * DatabaseConnector kobler til databasen og gjør spørring, for deretter å stenge tilkoblingen.
 *
 * @author VimCnett
 */
public class DatabaseConnector {
    private static final String QUERY_ERROR = "FEIL I SPØRRING";
    private static final String CONNECTION_ERROR = "FEIL VED TILKOBLING TIL DATABASE";
    private static final Integer ACTIVE = 1;
    private final String username = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/sks";

    public DatabaseConnector() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e + " Du har feil navn på databasedriveren.");
        }
    }
//EKSEMPEL PÅ HVORDAN STRUKTUREN SKAL VÆRE PÅ QUERIES

    public boolean leggTilBruker(Bruker bruker){
        if(bruker == null){
            return false;
        }
        try(Connection con = DriverManager.getConnection(url, username, password)){
            String query = "INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv)"
                    +"VALUES (?,?,?, ?,?,?)";
            try(PreparedStatement prepStat = con.prepareStatement(query)){
                con.setAutoCommit(false);
                prepStat.setString(1, bruker.getMail());
                prepStat.setInt(2, bruker.getRettighet());
                prepStat.setString(3, bruker.getFornavn());
                prepStat.setString(4, bruker.getEtternavn());
                prepStat.setString(5, bruker.genererPassord());
                prepStat.setInt(6, bruker.getAktiv());
                prepStat.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                return true;
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(QUERY_ERROR);
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(CONNECTION_ERROR);
            return false;
        }
    }

    /**
     *Oppdatterer en spesifikk bruker
     * @param bruker Den brukeren du il endre
     * @param mail mailen til den du skal endre, denne er i tilfelle man endrer mail
     * @return true om bruker blir oppdatert ellers false
     */
    public boolean oppdaterBruker(Bruker bruker, String mail){
        if(bruker == null){
            return false;
        }
        try(Connection con = DriverManager.getConnection(url, username, password)){
            String query = "UPDATE brukere SET mail = ?, rettighet_id = ?, fornavn = ?, etternavn = ?, passord = ?, aktiv = ?" +
                    "WHERE mail = ?";

            try(PreparedStatement prepStat = con.prepareStatement(query)){
                con.setAutoCommit(false);
                prepStat.setString(1, bruker.getMail());
                prepStat.setInt(2, bruker.getRettighet());
                prepStat.setString(3, bruker.getFornavn());
                prepStat.setString(4, bruker.getEtternavn());
                prepStat.setString(5, bruker.genererPassord());
                prepStat.setInt(6, bruker.getAktiv());
                prepStat.setString(7, mail);
                prepStat.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                return true;
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(QUERY_ERROR);
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(CONNECTION_ERROR);
            return false;
        }
    }

    /**
     * Tar inn en string som søkeord, søker i databasen etter mail, fornavn, etternavn som ligner på søkeordet.
     * @param soeketekst Søkeord etter bruker
     * @return ArrayList med bruker objekter eller null om ingen finnes.
     * @author GM
     */
    public ArrayList<Bruker> finnBruker(String soeketekst){
        if(soeketekst == null){
            return null;
        }
        try(Connection con = DriverManager.getConnection(url, username, password)){
            String query = "SELECT * FROM brukere WHERE mail LIKE ? OR fornavn LIKE ? OR etternavn LIKE ?";
            try(PreparedStatement prepStat = con.prepareStatement(query)){
                con.setAutoCommit(false);
                prepStat.setString(1, soeketekst);
                prepStat.setString(2, soeketekst);
                prepStat.setString(3, soeketekst);
                ResultSet rs = prepStat.executeQuery();
                con.commit();
                con.setAutoCommit(true);

                ArrayList<Bruker> brukere = new ArrayList<Bruker>();
                while(rs.next()){
                    Bruker objekt = new Bruker(rs.getString("mail"), rs.getInt("rettighet"), rs.getString("fornavn"), rs.getString("etternvn"), rs.getInt("aktiv"));
                    brukere.add(objekt);
                }
                return brukere;

            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(QUERY_ERROR);
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(CONNECTION_ERROR);
            return null;
        }
    }
}