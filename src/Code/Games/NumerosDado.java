// cSpell:ignore numeros
package Code.Games;

import java.util.Map;
import java.util.Random;

public class NumerosDado {
    private static final Random random = new Random();
    private static final Map<Integer, Integer> CUOTAS_SUMA = Map.ofEntries(
            Map.entry(2, 35),
            Map.entry(3, 17),
            Map.entry(4, 11),
            Map.entry(5, 8),
            Map.entry(6, 6),
            Map.entry(7, 5),
            Map.entry(8, 6),
            Map.entry(9, 8),
            Map.entry(10, 11),
            Map.entry(11, 17),
            Map.entry(12, 35));

    public static int numeroAleatorio() {
        return random.nextInt(1, 7);
    }

    public static int cuotaSumaPagar(int suma) {
        return CUOTAS_SUMA.getOrDefault(suma, 0);
    }
}