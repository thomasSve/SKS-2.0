package no.hist.tdat.javabeans.utils;

import no.hist.tdat.javabeans.Bruker;

import java.util.Random;

/**
 * Klassen tar seg av logikk for kryptering, generering og sammenligning av passord.
 *
 * @author vimCnett
 */
public class PassordService {
    private static final String RANDOM_TEGN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();



    /**
     * Hjelpemetode til genererPassord, generer random int verdier
     *
     * @return et tall mellom 0 og RANDOM_TEGN.length()
     */
    public static int randomIndex() {
        int min = 0;
        int max = RANDOM_TEGN.length();
        return random.nextInt(max);
    }

    /**
     * Tar inn en string fra brukeren og krypterer passordet.
     *
     * @param pw passord skrevet inn av bruker
     * @return String som kryptert passord med varierende lengde ( 4*pw.length )
     */
    public static String krypterPassord(String pw) {
        String alphaString = ("abcdefghijklmnopqrstuvwxyz").toUpperCase();
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz" + alphaString + "123567890").toCharArray();
        int length = alphabet.length;
        String kryptertPassord = "";
        int verdi = 0;
        int verdi2 = 0;
        char part1;
        char part2;
        char part3;
        char part4;
        for (int i = 0; i < pw.length(); i++) {
            verdi = (int) pw.charAt(i);
            verdi *= verdi;

            verdi2 = i * verdi * verdi2;

            part1 = (char) alphabet[(verdi % length)];
            part2 = (char) alphabet[((i * 103) % length)];
            part3 = (char) alphabet[(int) ((i * verdi2 * 708) % length)];
            part4 = (char) alphabet[(((1337 * verdi2 + verdi) % 713) % length)];

            kryptertPassord += part1;
            kryptertPassord += part2;
            kryptertPassord += part3;
            kryptertPassord += part4;
        }
        return krypterPassord2(kryptertPassord);
    }

    /**
     * Tar inn en kryptert string, lager en ny string med lengde 32
     *
     * @param krypt1 et kryptert passord med 4*passord.length lengde
     * @return Kryptert passord med lengde 32
     */
    private static String krypterPassord2(String krypt1) {
        int length = krypt1.length();
        int index = 0;
        String dobbelKrypt = "";
        for (int i = 0; i < 32; i++) {
            index = i;
            index %= length;
            dobbelKrypt += krypt1.charAt(index);
        }
        return dobbelKrypt;
    }

    /**
     * Generer et random passord på lengde 6 tegn
     *
     * @return kryptert random passord på lengde 6
     */
    public static String genererPassord() {
        String passord = "";
        for (int i = 0; i < 6; i++) {
            passord += RANDOM_TEGN.charAt(randomIndex());
        }
        System.out.println(passord);
        return passord;
    }

    /**
     *
     * @param gammeltP
     * @return
     */
    public static boolean sammenliknPassord(String gammeltP, Bruker bruker) {
        System.out.println(bruker.getPassord());
        System.out.println(PassordService.krypterPassord(gammeltP));
        return (PassordService.krypterPassord(gammeltP)).equals(bruker.getPassord());
    }
}
