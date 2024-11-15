// cSpell:ignore posicion
package Screens.Custom.Games.Carrera;

import java.util.ArrayList;
import java.util.List;
// 🚗
// 🚙
// 🎄
// 💥
// 🏁

class Pista {
    private int tamañoPista;
    private List<String> posiciones = new ArrayList<>();
    private Carro carro;

    public Pista(Carro carro) {
        this.tamañoPista = (int) (CarreraCarros.rand.nextInt(30, 40));
        this.rellenarPosiciones();
        this.colocarCarro();
        this.carro = carro;
    }

    public Pista(Carro carro, int tamaño) {
        this.tamañoPista = tamaño;
        this.carro = carro;
        this.rellenarPosiciones();
        this.colocarCarro();
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public void avanzar() {
        int paso = CarreraCarros.rand.nextInt(1, 4);
        while (this.carro.getPosicion() + paso >= this.tamañoPista)
            paso = CarreraCarros.rand.nextInt(1, 4);

        if (this.posiciones.get(this.carro.getPosicion()).equals("💥")) {
            this.posiciones.set(this.carro.getPosicion(), this.carro.getIcon());

        } else if (this.posiciones.get(this.carro.getPosicion() + paso).equals("🎄")) {
            this.posiciones.set(this.carro.getPosicion(), "_");
            this.posiciones.set(this.carro.getPosicion() + paso, "💥");
            this.carro.setPosicion(this.carro.getPosicion() + paso);

        } else {
            this.posiciones.set(this.carro.getPosicion(), "_");
            this.posiciones.set(this.carro.getPosicion() + paso, this.carro.getIcon());
            this.carro.setPosicion(this.carro.getPosicion() + paso);
        }
    }

    public String mostrarPista() {
        var pistaActual = carro.getNombreConductor().toUpperCase() + "   ";
        for (String i : posiciones) {
            pistaActual += i;
        }

        return pistaActual;
    }

    public int getTamañoPista() {
        return tamañoPista;
    }

    public void setTamañoPista(int tamañoPista) {
        this.tamañoPista = tamañoPista;
    }

    public void setPosiciones(List<String> posiciones) {
        this.posiciones = posiciones;
    }

    private void colocarCarro() {
        int posicionInicial = 0;
        this.carro.setPosicion(posicionInicial);
        this.posiciones.set(posicionInicial, this.carro.getIcon());
    }

    private void rellenarPosiciones() {
        for (int i = 0; i < this.tamañoPista; i++) {
            if (i == this.tamañoPista - 1) {
                this.posiciones.add("🏁");

            } else if ((CarreraCarros.rand.nextInt(1, 8)) == 5) {
                this.posiciones.add("🎄");
            } else {
                this.posiciones.add("_");
            }
        }
    }
}
