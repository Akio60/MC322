import java.util.ArrayList;

public class Ambiente {
    private int largura;
    private int altura;
    private int alturaMaxima;
    private ArrayList<Robo> robos;

    public Ambiente(int largura, int altura, int alturaMaxima) {
        this.largura = largura;
        this.altura = altura;
        this.alturaMaxima = alturaMaxima;
        this.robos = new ArrayList<>();
    }

    public void adicionarRobo(Robo robo) {
        robos.add(robo);
    }

    public boolean dentroDosLimites(int x, int y, int z) {
        return x >= 0 && x < largura && 
               y >= 0 && y < altura && 
               z >= 0 && z < alturaMaxima;
    }
}
