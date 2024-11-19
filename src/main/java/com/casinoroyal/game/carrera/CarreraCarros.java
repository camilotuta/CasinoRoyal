// cSpell:ignore GOOOOO Harvick
package main.java.com.casinoRoyal.game.carrera;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class CarreraCarros {
    public static Random rand = new Random();
    public static List<String> conductores = Arrays.asList("Kyle Larson", "Chase Elliott", "Denny Hamlin",
            "Kevin Harvick");
    public static List<String> iconosCarros = Arrays
            .asList("🚗", "🚙", "🚜", "🏍️", "🚚", "🚐", "🚑", "🚒", "🏎️", "🚓", "🚔", "🚕",
                    "🚖", "🚘", "🚛");
    public static final List<Color> coloresCarros = Arrays.asList(new java.awt.Color(168, 0, 1),
            new java.awt.Color(56, 168, 1),
            new java.awt.Color(117, 1, 117), new java.awt.Color(219, 110, 1));

    public List<Carro> ganadores = new ArrayList<>();
    private int tamañoPista = (int) rand.nextInt(70, 90);

    private int velocidadCarrera = rand.nextInt(200, 600);
    private List<Carro> carros = new ArrayList<>();
    private List<Pista> pistas = new ArrayList<>();
    private final JTextArea textArea;
    private final JLabel lbCuentaRegresiva;

    public CarreraCarros(JTextArea textArea, JLabel lbCuentaRegresiva) {
        this.textArea = textArea;
        this.textArea.setText("");
        this.lbCuentaRegresiva = lbCuentaRegresiva;
    }

    public int getVelocidadCarrera() {
        return velocidadCarrera;
    }

    public void setVelocidadCarrera(int velocidadCarrera) {
        this.velocidadCarrera = velocidadCarrera;
    }

    public int getTamañoPista() {
        return tamañoPista;
    }

    public void setTamañoPista(int tamañoPista) {
        this.tamañoPista = tamañoPista;
    }

    public List<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(List<Pista> pistas) {
        this.pistas = pistas;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public List<Carro> getGanador() {
        return ganadores;
    }

    public void setGanador(List<Carro> ganadores) {
        this.ganadores = ganadores;
    }

    public void iniciarCarrera() throws InterruptedException {
        this.llenarCarros();
        this.llenarPistas();
        this.cuentaRegresiva();
    }

    private void llenarCarros() {
        for (int i = 0; i < CarreraCarros.conductores.size(); i++) {
            String icono = iconosCarros.get(i);
            Carro carro = new Carro(icono, conductores.get(i), coloresCarros.get(i));
            this.carros.add(carro);
        }
    }

    private void llenarPistas() {
        for (int i = 0; i < CarreraCarros.conductores.size(); i++) {
            Carro carro = this.carros.get(i);
            Pista pista = new Pista(carro, this.tamañoPista);
            this.pistas.add(pista);
        }
    }

    public void mostrarPistas() {
        this.textArea.setText("");
        for (Pista i : this.pistas) {
            this.textArea.append("\n" + i.mostrarPista() + "\n\n");
        }
    }

    private void cuentaRegresiva() throws InterruptedException {
        this.mostrarPistas();
        for (int i = 3; i > 0; i--) {
            this.lbCuentaRegresiva.setText(String.valueOf(i));
            Thread.sleep(1000);
        }
        this.lbCuentaRegresiva.setText(" GOOOOO " + "\n");
        Thread.sleep(1000);
    }

    public void avanzarCarros() throws InterruptedException {
        for (Pista i : this.pistas) {
            i.avanzar();
        }
        Thread.sleep(velocidadCarrera);
    }

    public boolean hayGanador() {
        for (Carro i : this.carros) {
            if (i.getPosicion() >= tamañoPista - 1)
                this.ganadores.add(i);
        }
        return !this.ganadores.isEmpty();
    }

    public void mostrarGanador() {

    }
}