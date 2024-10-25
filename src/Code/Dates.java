// cSpell:words dias
package Code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Dates {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String obtenerAño() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public static String obtenerMes() {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    public static String obtenerDia() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    public static String obtenerFechaHoy() {
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    public static int restarFechasSinDiasBisiestos(String fMenorSinFormato, String fMayorSinFormato) {
        LocalDate fechaMenor = LocalDate.parse(fMenorSinFormato, formatter);
        LocalDate fechaMayor = LocalDate.parse(fMayorSinFormato, formatter);

        long diasEntre = ChronoUnit.DAYS.between(fechaMenor, fechaMayor);

        int diasBisiestos = 0;
        for (int año = fechaMenor.getYear(); año <= fechaMayor.getYear(); año++) {
            if (esAñoBisiesto(año) && (LocalDate.of(año, 2, 29).isAfter(fechaMenor)
                    || LocalDate.of(año, 2, 29).isBefore(fechaMayor))) {
                diasBisiestos++;
            }
        }
        return (int) Math.floor((diasEntre - diasBisiestos) / 365);
    }

    private static boolean esAñoBisiesto(int año) {
        return (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0));
    }

}
