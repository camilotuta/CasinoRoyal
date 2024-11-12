// cSpell:ignore posicion
package Screens.Custom.Games.Carrera;

// 🚗
// 🚙
// 🎄
// 💥
// 🏁

public class Carro {
    private String icono, nombreConductor;
    private int posicion;

    public Carro(String icono, String nombre) {
        this.nombreConductor = nombre;
        this.icono = icono;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getIcon() {
        return icono;
    }

    public void setIcon(String icono) {
        this.icono = icono;
    }
}