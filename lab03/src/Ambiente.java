package lab03.src;

import java.util.ArrayList;

/**
 * Classe que representa o ambiente da simulação.
 * Gerencia robôs e obstáculos através de composição.
 * Responsável por verificar limites e colisões.
 */
public class Ambiente {
    // Dimensões do ambiente
    private final int largura;
    private final int altura;
    // Listas de elementos - implementam composição
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

    /**
     * Verifica se uma posição está dentro dos limites do ambiente.
     * Usado para validar movimentações de robôs.
     */
    public boolean dentroDosLimites(int x, int y, int alt) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    public void adicionarObstaculo(Obstaculo o) {
        obstaculos.add(o);
    }

    public void removerObstaculo(Obstaculo o) {
        obstaculos.remove(o);
    }

    /**
     * Detecta colisões entre robôs e obstáculos.
     * Importante para a segurança da navegação.
     */
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
