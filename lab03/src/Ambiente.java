package LAB03.src;

import java.util.ArrayList;

import LAB03.src.Robo;

public class Ambiente {
    private final int largura;
    private final int altura;
    private final ArrayList<Robo> robos = new ArrayList<>();
    private final ArrayList<Obstaculo> obstaculos = new ArrayList<>();

    public Ambiente(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public void adicionarRobo(Robo r) {
        if (dentroDosLimites(r.getX(), r.getY(), r.getAltitude())) {
            robos.add(r);
        } else {
            throw new IllegalArgumentException("Robo fora dos limites do ambiente");
        }
    }

    public void removerRobo(Robo r) {
        robos.remove(r);
    }

    public boolean dentroDosLimites(int x, int y, int alt) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    public void adicionarObstaculo(Obstaculo o) {
        obstaculos.add(o);
    }

    public boolean detectarColisao(Robo r) {
        for (Obstaculo o : obstaculos) {
            if (o.bloqueiaPosicao(r.getX(), r.getY())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Robo> getRobos() {
        return robos;
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }
}
