package no.hist.tdat.javabeans.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Klassen tester klassen PassordService
 *
 * @author vimCnett
 */
public class PassordServiceTest {
    private final String helloWorld         = "Hello, World!";
    private final String helloWorldCrypt    = "0aakoQaKnxamneam0UarTBawWiagfYam";

    private final String hello              = "hello";
    private final String helloCrypt         = "taa0oQaKnxamneam0Uartaa0oQaKnxam";


    private final String helloWorld1 = "Hello, World!";

    @Test
    public void testRandomIndex() throws Exception {
        int index = PassordService.randomIndex();
        System.out.println(index);
        assertEquals(true, (index > 1 && index < 62));
    }

    @Test
    public void testKrypterPassord() throws Exception {
        String str = PassordService.krypterPassord(hello);
        System.out.println(str);
    }

    @Test
    public void testGenererPassord() throws Exception {

    }
}
