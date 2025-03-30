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

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMaxima(int alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }
}
