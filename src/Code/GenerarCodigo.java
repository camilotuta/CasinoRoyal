// cSpell:ignore codigo
package Code;

import java.util.Random;

public class GenerarCodigo {
    static Random random = new Random();

    public static String getCodigo() {
        return String.valueOf(random.nextInt(100_000, 999_999));
    }
}