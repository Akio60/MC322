package LAB03.src;

import Robo;

public class RoboAereo extends Robo {
    public RoboAereo(String nome, int x, int y, int altitude) {
        super(nome, x, y, altitude);
    }

    @Override
    public void mover(int dx, int dy, int dh) {
        this.x += dx;
        this.y += dy;
        this.altitude += dh;
        System.out.println("RoboAereo '" + nome + "' movido para (" + x + "," + y + ", alt=" + altitude + ")");
    }
}