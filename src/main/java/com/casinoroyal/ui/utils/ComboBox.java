// cspell:ignore rawtypes seleccion
package main.java.com.casinoRoyal.ui.utils;

import javax.swing.JComboBox;

public class ComboBox {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void vaciarCombo(JComboBox comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Seleccionar");
    }
}