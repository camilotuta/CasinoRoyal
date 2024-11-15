/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code.Games;

import java.util.Random;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tutaa
 */
public class CasillasRuleta {
    private static final Random random = new Random();

    private static final List<Integer> redNumbers = Arrays.asList(
            1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    private static final List<Integer> blackNumbers = Arrays.asList(
            2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);

    private static final List<Integer> greenNumbers = Arrays.asList(0);

    public static String colorCasilla(int numeroCasilla) {
        if (redNumbers.contains(numeroCasilla)) {
            return "Rojo";
        } else if (blackNumbers.contains(numeroCasilla)) {
            return "Negro";
        } else if (greenNumbers.contains(numeroCasilla)) {
            return "Verde";
        }
        return "";
    }

    public static int casillaAleatoria() {
        return random.nextInt(0, 37);

    }
}