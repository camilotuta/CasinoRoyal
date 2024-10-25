//cSpell:ignore condicion
package Code;

import javax.swing.JLabel;

import Screens.Custom.CambiarIU;
import Screens.Custom.ObtenerIU;
import Screens.Signup.Signup;

public class VerificarDato {

    public static void verificarCampo(boolean condicion, JLabel labelPonerImagen, String toolTipTextSi,
            String toolTipTextNo) {

        if (condicion) {
            ponerImagenError(labelPonerImagen, toolTipTextNo);
        } else {
            ponerImagenCheck(labelPonerImagen, toolTipTextSi);
        }
    }

    public static void verificarFechaNacimiento(boolean condicion, JLabel labelPonerImagen, String toolTipTextSi,
            String toolTipTextNo) {
        if (condicion) {
            ponerImagenError(labelPonerImagen, toolTipTextNo);
            Signup.fechaValida = false;
        } else {
            if (Dates.restarFechasSinDiasBisiestos(ObtenerIU.obtenerFechaSeleccionada(Signup.datePicker),
                    Dates.obtenerFechaHoy()) >= 18) {
                ponerImagenCheck(labelPonerImagen, toolTipTextSi);
                Signup.fechaValida = true;
            } else {
                ponerImagenError(labelPonerImagen, toolTipTextNo);
                Signup.fechaValida = false;
            }
        }
    }

    public static void ponerImagenError(JLabel labelPonerImagen, String toolTipTextNo) {
        CambiarIU.setImageLabel(labelPonerImagen,
                "src/img/error.png");
        labelPonerImagen.setToolTipText(toolTipTextNo);
    }

    public static void ponerImagenCheck(JLabel labelPonerImagen, String toolTipTextSi) {
        CambiarIU.setImageLabel(labelPonerImagen,
                "src/img/check.png");
        labelPonerImagen.setToolTipText(toolTipTextSi);
    }

}
