package no.hist.tdat.database;

import no.hist.tdat.javabeans.Bruker;
import no.hist.tdat.koe.KoeBruker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public boolean leggTilBruker(Bruker bruker) {
        if (bruker == null) {
            return false;
        }
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv)"
                    + "VALUES (?,?,?, ?,?,?)";
            try (PreparedStatement prepStat = con.prepareStatement(query)) {
                con.setAutoCommit(false);
                prepStat.setString(1, bruker.getMail());
                prepStat.setString(2, bruker.getRettighet());
                prepStat.setString(3, bruker.getFornavn());
                prepStat.setString(4, bruker.getEtternavn());
                prepStat.setString(5, bruker.genererPassord());
                prepStat.setInt(6, ACTIVE);
                prepStat.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(QUERY_ERROR);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(CONNECTION_ERROR);
            return false;
        }
    }

    public boolean leggTilBrukerIKo(KoeBruker koeBruker) {
        if (koeBruker == null) {
            return false;
        }
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO koe_brukere (koe_id, mail, plassering, ovingsnummer, koe_plass)" + "VALUES (?,?,?,?,?)";
            try (PreparedStatement prepStat = con.prepareStatement(query)) {
                con.setAutoCommit(false);
                prepStat.setInt(1, koeBruker.getKoe_id());
                prepStat.setString(2, koeBruker.getMail());
                prepStat.setString(3, koeBruker.getPlassering());
                prepStat.setString(4, koeBruker.getOvingsnr());
                prepStat.setInt(5, koeBruker.getKoe_plass());
                prepStat.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(QUERY_ERROR);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(CONNECTION_ERROR);
            return false;
        }
    }
}
