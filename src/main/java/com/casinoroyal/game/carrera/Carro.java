// cSpell:ignore posicion
package main.java.com.casinoRoyal.game.carrera;

import java.awt.Color;

// 🚗
// 🚙
// 🎄
// 💥
// 🏁

public class Carro {
    private String icon, nombreConductor;
    private int posicion;
    private Color color;

    public Carro(String icon, String nombre, Color color) {
        this.nombreConductor = nombre;
        this.icon = icon;
        this.color = color;
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
        return icon;
    }

    public void setIcon(String icono) {
        this.icon = icono;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}