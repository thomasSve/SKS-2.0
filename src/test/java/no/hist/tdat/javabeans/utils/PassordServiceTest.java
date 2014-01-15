package no.hist.tdat.javabeans.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Klassen tester klassen PassordService
 *
 * @author vimCnett
 */
public class PassordServiceTest {
    private final String[] hellos = {
            "Hello, World",
            "hello",
            "Hello",
            "hello, world"
    };
    private final String[] hash = {
            "0aakoQaKnxamneam0UarTBawWiagfYam",
            "taa0oQaKnxamneam0Uartaa0oQaKnxam",
            "0aakoQaKnxamneam0Uar0aakoQaKnxam",
            "taa0oQaKnxamneam0UarTBawWiagjYae"
    };

    @Test
    public void testRandomIndex(){
        for (int i = 0; i < 100000; i++) {
            int index = PassordService.randomIndex();
                assertEquals(true, (index >= 0 && index <= 62));
        }
    }

    @Test
    public void testKrypterPassord() throws Exception {
        for (int i = 0; i < hellos.length; i++) {
            assertEquals(hash[i], PassordService.krypterPassord(hellos[i]));
        }
    }

    @Test
    public void testGenererPassord() throws Exception {
        for (int i = 0; i < 100000; i++) {
            assertTrue((PassordService.genererPassord().length() == 6));
        }
    }
}
