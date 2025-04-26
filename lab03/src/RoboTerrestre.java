package LAB03.src;

import Robo;

public class RoboTerrestre extends Robo {
    public RoboTerrestre(String nome, int x, int y) {
        super(nome, x, y, 0);
    }

    @Override
    public void mover(int dx, int dy, int dh) {
        this.x += dx;
        this.y += dy;
        System.out.println("RoboTerrestre '" + nome + "' movido para (" + x + "," + y + ")");
    }
}