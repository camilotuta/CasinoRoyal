// cSpell:ignore codigo
package main.java.com.casinoRoyal.service.communication;

import java.util.Random;

public class GenerarCodigo {
    static Random random = new Random();

    public static String getCodigo() {
        return String.valueOf(random.nextInt(100_000, 999_999));
    }
}