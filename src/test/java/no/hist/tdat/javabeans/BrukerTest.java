package no.hist.tdat.javabeans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester brukerklassen
 *
 *
 */
public class BrukerTest {
    Bruker bruker;
    private String  email     = "jorgen@guut.org";
    private String  fornavn   = "Jørgen Lien";
    private String  fornavn_n = "Geir Morten";
    private String  etternan  = "Sellæg";
    private String  etternan_n= "Larsen";
    private int     id        = 1;
    private int     id_new    = 5;
    private String  email_new = "jorgels@stud.hist.no";

    @Before
    public void setUp() throws Exception {
        bruker = new Bruker(email, 1, fornavn, etternan);
        bruker.setPassord(email);
        bruker.setAktiv(id);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMail() throws Exception {
        assertEquals(email, bruker.getMail());
    }

    @Test
    public void testSetMail() throws Exception {
        bruker.setMail(email_new);
        assertEquals(email_new, bruker.getMail());
    }

    @Test
    public void testGetRettighet() throws Exception {
        int i = bruker.getRettighet();
        System.out.println(i);
        assertEquals(id, i);
    }

    @Test
    public void testSetRettighet() throws Exception {
        bruker.setRettighet(id_new);
        int i = bruker.getRettighet();
        assertEquals(id_new, i);
    }

    @Test
    public void testGetFornavn() throws Exception {
        assertEquals(fornavn, bruker.getFornavn());
    }

    @Test
    public void testSetFornavn() throws Exception {
        bruker.setFornavn(fornavn_n);
        assertEquals(fornavn_n, bruker.getFornavn());
    }

    @Test
    public void testGetEtternavn() throws Exception {
        assertEquals(etternan, bruker.getEtternavn());
    }

    @Test
    public void testSetEtternavn() throws Exception {
        bruker.setEtternavn(etternan_n);
        assertEquals(etternan_n, bruker.getEtternavn());
    }

    @Test
    public void testGetAktiv() throws Exception {
        assertEquals(id, bruker.getAktiv());
    }

    @Test
    public void testSetAktiv() throws Exception {
        bruker.setAktiv(id_new);
        assertEquals(id_new, bruker.getAktiv());
    }
/*

//TODO skriv disse testene når du har nyeste versjon av bruker.

    @Test
    public void testEndrePassord() throws Exception {

    }

    @Test
    public void testGetBekreftPassord() throws Exception {

    }

    @Test
    public void testSetBekreftPassord() throws Exception {

    }

    @Test
    public void testGetGammeltPassord() throws Exception {

    }

    @Test
    public void testSetGammeltPassord() throws Exception {

    }

    @Test
    public void testGetNyttPassord() throws Exception {

    }

    @Test
    public void testSetNyttPassord() throws Exception {

    }

    */
}
