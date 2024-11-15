// cSpell:ignore numeros
package Code.Games;

import java.util.Random;

public class CasillasTragaMonedas {
    private static final Random random = new Random();

    public static int casillaAleatorio() {
        return random.nextInt(0, 4);
    }
}