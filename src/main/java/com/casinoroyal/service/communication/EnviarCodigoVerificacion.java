// cSpell:ignore verificacion codigo
package main.java.com.casinoRoyal.service.communication;

public class EnviarCodigoVerificacion extends EnviarCorreo {

    private int intentos = 3;
    private String codigo;

    public EnviarCodigoVerificacion(String correoEnviar, String asunto, String mensaje, String codigo) {
        super(correoEnviar, asunto, mensaje);
        this.codigo = codigo;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
