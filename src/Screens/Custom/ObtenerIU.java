// cSpell:ignore rawtypes seleccion
package Screens.Custom;

import static Code.Dates.formatter;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import raven.datetime.component.date.DatePicker;

public class ObtenerIU {

    public static char[] obtenerContraseña(JPasswordField pfContraseña) {
        return pfContraseña.getPassword();
    }

    public static String obtenerTextoEtiqueta(JLabel label) {
        return (String) label.getText();
    }

    public static String obtenerTextoPanel(JTextPane panel) {
        return (String) panel.getText();
    }

    public static String obtenerTextoArea(JTextArea area) {
        return (String) area.getText();
    }

    public static String obtenerTextoCampo(JTextField area) {
        return (String) area.getText();
    }

    @SuppressWarnings("rawtypes")
    public static String obtenerSeleccionCombo(JComboBox comboBox) {
        Object selectedItem = comboBox.getSelectedItem();
        return selectedItem != null ? selectedItem.toString() : null;
    }

    @SuppressWarnings("rawtypes")
    public static int obtenerIndiceSeleccionCombo(JComboBox comboBox) {
        return comboBox.getSelectedIndex();
    }

    public static String obtenerFechaSeleccionada(DatePicker datePicker) {
        return formatter.format(datePicker.getSelectedDate());
    }
}
